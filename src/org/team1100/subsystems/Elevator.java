package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.manipulator.elevator.UserElevatorCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class Elevator extends PIDSubsystem {

	private static Elevator elevator;

	private static final String topKey = "Elevator_Top";
	private static final String platKey = "Elevator_Plat";
	private static final String botKey = "Elevator_Bot";
	private static final String pKey = "Elevator_P";
	private static final String iKey = "Elevator_I";
	private static final String dKey = "Elevator_D";

	private static int TOP_SETPOINT = 9000;
	private static int PLATFORM_SETPOINT = 0; // TODO Find setpoint
	private static int BOTTOM_SETPOINT = 0;

	private static double P = .001;
	private static double I = 0;
	private static double D = .001;

	public static Elevator getInstance() {
		if (elevator == null)
			elevator = new Elevator();
		updatePreferences(elevator);
		return elevator;
	}
	
	public static void updatePreferences(Elevator elevaotr) {
		P = Preferences.getInstance().getDouble(pKey, P);
		I = Preferences.getInstance().getDouble(iKey, I);
		D = Preferences.getInstance().getDouble(dKey, D);
		elevaotr.getPIDController().setPID(P, I, D);
		TOP_SETPOINT = Preferences.getInstance().getInt(topKey, TOP_SETPOINT);
		PLATFORM_SETPOINT = Preferences.getInstance().getInt(topKey, PLATFORM_SETPOINT);
		BOTTOM_SETPOINT = Preferences.getInstance().getInt(topKey, BOTTOM_SETPOINT);
	}

	private boolean encoderReset = false;
	private ElevatorDrive elevatorDrive;
	private Encoder encoder;
	private DigitalInput beamBreak;
	private DigitalInput infraredSensorBack;
	private DigitalInput infraredSensorFront;
	private DoubleSolenoid pushingPiston;

	private Elevator() {
		super(P, I, D);
		this.getPIDController().setContinuous();
		elevatorDrive = new ElevatorDrive(RobotMap.E_ELEVATOR_CIM_1, RobotMap.E_ELEVATOR_CIM_2);

		encoder = new Encoder(RobotMap.E_ENCODER_A, RobotMap.E_ENCODER_B);
		beamBreak = new DigitalInput(RobotMap.E_BEAM_BREAK);
		infraredSensorBack = new DigitalInput(RobotMap.E_INFRARED_SENSOR_BACK);
		infraredSensorFront = new DigitalInput(RobotMap.E_INFRARED_SENSOR_FRONT);
		pushingPiston = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.E_PUSHING_PISTON_A,RobotMap.E_PUSHING_PISTON_B);

		pushingPiston.set(Value.kReverse);
		setAbsoluteTolerance(250);

		LiveWindow.addActuator("Elevator", "Encoder", encoder);
		LiveWindow.addSensor("Elevator", "Beam Break", beamBreak);
		LiveWindow.addSensor("Elevator", "Infrared Sensor", infraredSensorBack);
		LiveWindow.addSensor("Elevator", "Infrared Sensor", infraredSensorFront);
		LiveWindow.addSensor("Elevator", "PID Controller", getPIDController());
		LiveWindow.addActuator("Elevator", "Elevator Drive", elevatorDrive);
		LiveWindow.addActuator("Elevator", "Pushing Piston", pushingPiston);

		Preferences.getInstance().putDouble(pKey, P);
		Preferences.getInstance().putDouble(iKey, I);
		Preferences.getInstance().putDouble(dKey, D);
		Preferences.getInstance().putInt(topKey, TOP_SETPOINT);
		Preferences.getInstance().putInt(platKey, PLATFORM_SETPOINT);
		Preferences.getInstance().putInt(botKey, BOTTOM_SETPOINT);
	}

	public void lift(double speed) {
		if (!beamBreak.get() && speed < 0)
			speed = 0;
		elevatorDrive.lift(speed);
	}

	public double getPosition() {
		return encoder.get();
	}

	public double getSpeed() {
		return encoder.getRate();
	}

	public void resetEncoder() {
		encoder.reset();
		encoderReset = true;
	}

	public boolean isEncoderReset() {
		return encoderReset;
	}

	/**
	 * Default: true
	 */
	public boolean getBeamBreak() {
		return beamBreak.get();
	}

	public boolean isToteInElevator() {
		return !infraredSensorBack.get();
	}

	public boolean isToteOutOfElevator() {
		return infraredSensorFront.get();
	}

	@Override
	protected double returnPIDInput() {
		return getPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		lift(output);
	}

	public void goToBottom() {
		setSetpoint(BOTTOM_SETPOINT);
	}

	public void goToTop() {
		setSetpoint(TOP_SETPOINT);
	}

	public void goToPlatformHeigh() {
		setSetpoint(PLATFORM_SETPOINT);
	}
	
	public void pushOutPiston(){
		pushingPiston.set(Value.kForward);
	}
	
	public void pullInPiston(){
		pushingPiston.set(Value.kReverse);
	}


	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserElevatorCommand());
	}

	class ElevatorDrive implements LiveWindowSendable {

		private CANTalon talon1;
		private CANTalon talon2;
		private ITable m_table;
		private ITableListener m_table_listener;

		public ElevatorDrive(int channel1, int channel2) {
			talon1 = new CANTalon(channel1);
			talon2 = new CANTalon(channel2);
		}

		@Override
		public void initTable(ITable subtable) {
			m_table = subtable;
			updateTable();

		}

		@Override
		public ITable getTable() {
			return m_table;
		}

		@Override
		public String getSmartDashboardType() {
			return "Speed Controller";
		}

		@Override
		public void updateTable() {
			if (m_table != null) {
				m_table.putNumber("Value1", talon1.getSpeed());
				m_table.putNumber("Value2", talon2.getSpeed());
			}
		}

		@Override
		public void startLiveWindowMode() {
			talon1.set(0);
			talon2.set(0);
			m_table_listener = new ITableListener() {
				public void valueChanged(ITable itable, String key, Object value, boolean bln) {
					lift(((Double) value).doubleValue());
				}
			};
			m_table.addTableListener("Value", m_table_listener, true);

		}

		@Override
		public void stopLiveWindowMode() {
			talon1.set(0);
			talon2.set(0);

		}

		public void lift(double speed) {
			talon1.set(-speed);
			talon2.set(-speed);
			SmartDashboard.putNumber("Talon Current",
					talon1.getOutputCurrent() + talon2.getOutputCurrent());
		}

	}

}
