package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.manipulator.elevator.UserMoveElevator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

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

	private Encoder encoder;
	private DigitalInput limitSwitch;
	private WPI_TalonSRX talon;

	private Elevator() {
		super(P, I, D);
		
		encoder = new Encoder(RobotMap.E_ENCODER_A, RobotMap.E_ENCODER_B);
		encoder.setReverseDirection(true);
		limitSwitch = new DigitalInput(RobotMap.E_LIMIT_SWITCH);
		talon = new WPI_TalonSRX(RobotMap.E_ELEVATOR_CIM_1);

		setInputRange(BOTTOM, TOP);  // TODO Check if works
		setAbsoluteTolerance(ABSOLUTE_TOLERANCE);
		enable();

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

		talon.set(-speed);
	}

	public void resetEncoder() {
		encoder.reset();
	}

	public boolean isBeamBroken() {
		return !limitSwitch.get();
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
		//SmartDashboard.putNumber("Encoder", getPosition());
	}

}