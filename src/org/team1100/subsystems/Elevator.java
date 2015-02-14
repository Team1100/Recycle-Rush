package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.manipulator.UserElevatorCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Elevator extends PIDSubsystem {

	private static Elevator elevator = null;
	private static double TOP_SETPOINT = 1; // TODO Find the top of the elevator
	private static double BOTTOM_SETPOINT = 0;
	private static double P = 1 / TOP_SETPOINT;
	private static double I = 0;
	private static double D = 0;

	public static Elevator getInstance() {
		if (elevator == null)
			elevator = new Elevator();
		return elevator;
	}

	private boolean encoderReset = false;
	private CANTalon talon1;
	private CANTalon talon2;
	private Encoder encoder;
	private DigitalInput beamBreak;
	private DigitalInput infraredSensor;

	private Elevator() {
		super(P, I, D);
		this.getPIDController().setContinuous();
		talon1 = new CANTalon(RobotMap.M_ELEVATOR_CIM_1);
		talon2 = new CANTalon(RobotMap.M_ELEVATOR_CIM_2);

		encoder = new Encoder(RobotMap.M_ENCODER_A, RobotMap.M_ENCODER_B);
		beamBreak = new DigitalInput(RobotMap.M_BEAM_BREAK);
		infraredSensor = new DigitalInput(RobotMap.M_INFRARED_SENSOR);

		LiveWindow.addSensor("Elevator", "Encoder", encoder);
		LiveWindow.addSensor("Elevator", "Beam Break", beamBreak);
		LiveWindow.addSensor("Elevator", "Infrared Sensor", infraredSensor);
		LiveWindow.addSensor("Elevator", "PID Controller", getPIDController());

		Preferences.getInstance().putDouble("P", P);
		Preferences.getInstance().putDouble("I", I);
		Preferences.getInstance().putDouble("D", D);
	}

	public void lift(double speed) {
		if (beamBreak.get())
			speed = 0;
		talon1.set(speed);
		talon2.set(speed);
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

	public boolean getBeamBreak() {
		return beamBreak.get();
	}

	public boolean isToteInElevator() {
		return infraredSensor.get();
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

	public void updatePID() {
		P = Preferences.getInstance().getDouble("P", P);
		I = Preferences.getInstance().getDouble("I", I);
		D = Preferences.getInstance().getDouble("D", D);
		getPIDController().setPID(P, I, D);
	}

	@Override
	public void setSetpoint(double setpoint) {
		updatePID();
		super.setSetpoint(setpoint);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserElevatorCommand());
	}
}
