package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.manipulator.elevator.UserMoveElevator;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class Elevator extends PIDSubsystem {

	public static int TOP = 8500;
	public static int DRIVING_HEIGHT = 2500;
	public static int BOTTOM = 0;

	private static Elevator elevator;

	private static final String topKey = "ElevatorTop";
	private static final String driveKey = "ElevatorDrive";
	private static final String botKey = "ElevatorBot";
	private static final String pKey = "ElevatorP";
	private static final String iKey = "ElevatorI";
	private static final String dKey = "ElevatorD";
	private static final String toleranceKey = "ElevatorTolerence";

	private static double P = .05;
	private static double I = 0;
	private static double D = 0;
	private static int ABSOLUTE_TOLERANCE = 300;

	public static Elevator getInstance() {
		if (elevator == null)
			elevator = new Elevator();
		updatePreferences();
		return elevator;
	}

	public static void updatePreferences() {
		P = Preferences.getInstance().getDouble(pKey, P / 100.0) / 100.0;
		I = Preferences.getInstance().getDouble(iKey, I);
		D = Preferences.getInstance().getDouble(dKey, D);
		ABSOLUTE_TOLERANCE = Preferences.getInstance().getInt(toleranceKey, ABSOLUTE_TOLERANCE);

		elevator.getPIDController().setPID(P, I, D);
		elevator.setAbsoluteTolerance(ABSOLUTE_TOLERANCE);

		TOP = Preferences.getInstance().getInt(topKey, TOP);
		DRIVING_HEIGHT = Preferences.getInstance().getInt(driveKey, DRIVING_HEIGHT);
		BOTTOM = Preferences.getInstance().getInt(botKey, BOTTOM);
	}

	private ElevatorDrive elevatorDrive;
	private Encoder encoder;
	private DigitalInput beamBreak;

	private Elevator() {
		super(P, I, D);
		elevatorDrive = new ElevatorDrive(RobotMap.E_ELEVATOR_CIM_1);

		encoder = new Encoder(RobotMap.E_ENCODER_A, RobotMap.E_ENCODER_B);
		encoder.setReverseDirection(true);
		beamBreak = new DigitalInput(RobotMap.E_BEAM_BREAK);

		// setOutputRange(BOTTOM, TOP);
		setAbsoluteTolerance(ABSOLUTE_TOLERANCE);
		enable();

		LiveWindow.addActuator("Elevator", "Encoder", encoder);
		LiveWindow.addSensor("Elevator", "Beam Break", beamBreak);
		LiveWindow.addSensor("Elevator", "PID Controller", getPIDController());
		LiveWindow.addActuator("Elevator", "Elevator Drive", elevatorDrive);

		Preferences.getInstance().putDouble(pKey, P);
		Preferences.getInstance().putDouble(iKey, I);
		Preferences.getInstance().putDouble(dKey, D);
		Preferences.getInstance().putInt(topKey, TOP);
		Preferences.getInstance().putInt(driveKey, DRIVING_HEIGHT);
		Preferences.getInstance().putInt(botKey, BOTTOM);
		Preferences.getInstance().putInt(toleranceKey, ABSOLUTE_TOLERANCE);
	}

	public void lift(double speed) {
		if (speed < 0 && isBeamBroken()) {
			speed = 0;
			resetEncoder();
		}

		elevatorDrive.lift(speed);
	}

	public void setSetpoint(double point) {
		if (point < 0)
			super.setSetpoint(0);
		else if (point > TOP)
			super.setSetpoint(TOP);
		else
			super.setSetpoint(point);
	}

	public void resetEncoder() {
		encoder.reset();
	}

	public boolean isBeamBroken() {
		return !beamBreak.get();
	}



	@Override
	protected double returnPIDInput() {
		return encoder.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		lift(output);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserMoveElevator());
	}

	public void log() {
		SmartDashboard.putNumber("Encoder", getPosition());
	}

	private class ElevatorDrive implements LiveWindowSendable {

		private CANTalon talon1;
		private ITable m_table;
		private ITableListener m_table_listener;

		public ElevatorDrive(int channel1) {
			talon1 = new CANTalon(channel1);
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
			}
		}

		@Override
		public void startLiveWindowMode() {
			talon1.set(0);
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
		}

		public void lift(double speed) {
			talon1.set(-speed);
		}

	}

}
