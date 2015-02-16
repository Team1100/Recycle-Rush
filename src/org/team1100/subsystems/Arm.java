package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.manipulator.arm.UserArmCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Arm extends PIDSubsystem {

	public static Arm arm;

	private static final String pKey = "Arm_P";
	private static final String iKey = "Arm_I";
	private static final String dKey = "Arm_D";

	private static double P = 0;
	private static double I = 0;
	private static double D = 0;

	public static Arm getInstatnce() {
		if (arm == null)
			arm = new Arm();
		updatePreferences(arm);
		return arm;
	}

	private static void updatePreferences(Arm arm) {
		P = Preferences.getInstance().getDouble(pKey, P);
		I = Preferences.getInstance().getDouble(iKey, I);
		D = Preferences.getInstance().getDouble(dKey, D);
		arm.getPIDController().setPID(P, I, D);
	}

	private Talon talonLeft;
	private Talon talonRight;
	private Encoder encoder;

	private Arm() {
		super(P, I, D);

		talonLeft = new Talon(RobotMap.A_LEFT_MOTOR);
		talonRight = new Talon(RobotMap.A_RIGHT_MOTOR);

		encoder = new Encoder(RobotMap.A_ENCODER_A, RobotMap.A_ENCODER_B);

		LiveWindow.addActuator("Arm", "Encoder", encoder);

		Preferences.getInstance().putDouble(pKey, P);
		Preferences.getInstance().putDouble(iKey, I);
		Preferences.getInstance().putDouble(dKey, D);
	}

	@Override
	protected double returnPIDInput() {
		return getPosition();
	}

	public double getPosition() {
		return encoder.get();
	}
	
	public void moveArm(double speed){
		talonLeft.set(speed);
		talonRight.set(-speed);
	}

	@Override
	protected void usePIDOutput(double output) {
		moveArm(output);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserArmCommand());
	}
}
