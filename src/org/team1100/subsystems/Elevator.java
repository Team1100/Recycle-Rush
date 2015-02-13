package org.team1100.subsystems;

import org.team1100.OI;
import org.team1100.Robot;
import org.team1100.RobotMap;
import org.team1100.commands.manipulator.UserElevatorCommand;
import org.team1100.input.XboxController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends PIDSubsystem {

	public static Elevator elevator = null;

	public static Elevator getInstance() {
		if (elevator == null)
			elevator = new Elevator();
		return elevator;
	}

	private static double SPEED_PERCENT = 0.5;
	private static double TOP_SETPOINT = 1; // TODO Find the top of the elevator
	private static double BOTTOM_SETPOINT = 0;

	private boolean encoderReset = false;

	private CANTalon talon1;
	private CANTalon talon2;
	private Encoder encoder;
	private DigitalInput beamBreak;

	private Elevator() {
		super(1 / TOP_SETPOINT, 0, 0);
		this.getPIDController().setContinuous();
		talon1 = new CANTalon(RobotMap.M_ELEVATOR_CIM_1);
		talon2 = new CANTalon(RobotMap.M_ELEVATOR_CIM_2);

		encoder = new Encoder(RobotMap.M_ENCODER_A, RobotMap.M_ENCODER_B);
		beamBreak = new DigitalInput(RobotMap.M_ENCODER_A);
	}

	public void userLift() {
		double speed = 0;
		double leftSpeed = OI.getInstance().getXboxController()
				.getAxis(XboxController.XboxAxis.kLeftTrigger);
		double rightSpeed = OI.getInstance().getXboxController()
				.getAxis(XboxController.XboxAxis.kRightTrigger);

		if (leftSpeed != 0)
			speed = leftSpeed * SPEED_PERCENT;
		else if (rightSpeed != 0)
			speed = -rightSpeed * SPEED_PERCENT;

		lift(speed);
	}

	public void lift(double speed) {
		if (beamBreak.get())
			speed = 0;
		talon1.set(speed);
		talon2.set(speed);
		// SmartDashboard.putNumber("Talon 1 Current",
		// talon1.getOutputCurrent());
		// SmartDashboard.putNumber("Talon 2 Current",
		// talon2.getOutputCurrent());

		// SmartDashboard.putNumber("Lift Current", talon1.getOutputCurrent() +
		// talon2.getOutputCurrent());
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
		enable();
	}

	public void goToTop() {
		setSetpoint(TOP_SETPOINT);
		enable();
	}

	public boolean getBeamBreak() {
		return beamBreak.get();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserElevatorCommand());
	}
}
