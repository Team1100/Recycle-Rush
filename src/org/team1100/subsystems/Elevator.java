package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.manipulator.UserElevatorCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Elevator extends PIDSubsystem {

	private static Elevator elevator = null;

	public static Elevator getInstance() {
		if (elevator == null)
			elevator = new Elevator();
		return elevator;
	}

	private static double TOP_SETPOINT = 1; // TODO Find the top of the elevator
	private static double BOTTOM_SETPOINT = 0;

	private boolean encoderReset = false;

	private CANTalon talon1;
	private CANTalon talon2;
	private Encoder encoder;
	private DigitalInput beamBreak;
	private DigitalInput infraredSensor;

	private Elevator() {
		super(1 / TOP_SETPOINT, 0, 0);
		this.getPIDController().setContinuous();
		talon1 = new CANTalon(RobotMap.M_ELEVATOR_CIM_1);
		talon2 = new CANTalon(RobotMap.M_ELEVATOR_CIM_2);

		encoder = new Encoder(RobotMap.M_ENCODER_A, RobotMap.M_ENCODER_B);
		beamBreak = new DigitalInput(RobotMap.M_ENCODER_A);
		infraredSensor = new DigitalInput(RobotMap.M_INFRARED_SENSOR);
	}

	public void lift(double speed) {
		if (beamBreak.get())
			speed = 0;
		talon1.set(speed);
		talon2.set(speed);
	}

	public void stop() {
		lift(0);
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

	public boolean getBeamBreak() {
		return beamBreak.get();
	}
	
	public boolean isToteInElevator(){
		return infraredSensor.get();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserElevatorCommand());
	}
}
