package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.manipulator.arm.UserArmCommand;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Arm extends PIDSubsystem {

	public static Arm arm;

	private static final String pKey = "Arm_P";
	private static final String iKey = "Arm_I";
	private static final String dKey = "Arm_D";

	private static double P = 0;
	private static double I = 0;
	private static double D = 0;

	public static int TOP_SETPOINT = 0; // TODO Find setpoint
	public static int SCORING_SETPOINT = 0; // TODO Find setpoint

	public static Arm getInstance() {
		if (arm == null)
			arm = new Arm();
		updatePreferences();
		return arm;
	}

	private static void updatePreferences() {
		P = Preferences.getInstance().getDouble(pKey, P);
		I = Preferences.getInstance().getDouble(iKey, I);
		D = Preferences.getInstance().getDouble(dKey, D);
		arm.getPIDController().setPID(P, I, D);

	}

	private Talon talonLeft;
	private Talon talonRight;
	private DoubleSolenoid gripperSolenoid;
	private DoubleSolenoid clawRotateSolenoid;
	private AnalogPotentiometer pot;

	private boolean isGripperClamped;
	private boolean isClawRotated;

	private Arm() {
		super(P, I, D);

		talonLeft = new Talon(RobotMap.A_LEFT_MOTOR);
		talonRight = new Talon(RobotMap.A_RIGHT_MOTOR);

		pot = new AnalogPotentiometer(RobotMap.A_POTENTIOMETER);

		gripperSolenoid = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.A_GRIPPER_SOLENOID_A, RobotMap.A_GRIPPER_SOLENOID_B);
		clawRotateSolenoid = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.A_CLAW_ROTATE_SOLENOID_A, RobotMap.A_CLAW_ROTATE_SOLENOID_B);

		gripperSolenoid.set(Value.kReverse);
		clawRotateSolenoid.set(Value.kReverse);

		isGripperClamped = false;
		isClawRotated = false;

		LiveWindow.addActuator("Arm", "Potentiometer", pot);
		LiveWindow.addActuator("Arm", "Right Talon", talonRight);
		LiveWindow.addActuator("Arm", "Left Talon", talonLeft);
		LiveWindow.addActuator("Arm", "Toggle Gripper", gripperSolenoid);
		LiveWindow.addActuator("Arm", "Rotate Claw", clawRotateSolenoid);
		LiveWindow.addSensor("Arm", "Potentiometer", pot);
		LiveWindow.addActuator("Arm", "PID Controller", getPIDController());

		Preferences.getInstance().putDouble(pKey, P);
		Preferences.getInstance().putDouble(iKey, I);
		Preferences.getInstance().putDouble(dKey, D);
	}

	@Override
	protected double returnPIDInput() {
		return getPosition();
	}

	public double getPosition() {
		return pot.get();
	}

	public void moveArm(double speed) {
		talonLeft.set(speed);
		talonRight.set(-speed);
	}

	public void toggleGripper() {
		if (isGripperClamped)
			gripperSolenoid.set(Value.kReverse);
		else
			gripperSolenoid.set(Value.kForward);
		isGripperClamped = !isGripperClamped;
	}

	public void clampGripper() {
		if (!isGripperClamped) {
			gripperSolenoid.set(Value.kForward);
			isGripperClamped = true;
		}
	}

	public void unclampGripper() {
		if (isGripperClamped) {
			gripperSolenoid.set(Value.kReverse);
			isGripperClamped = false;
		}
	}

	public void toggleRotateClaw() {
		if (isClawRotated)
			clawRotateSolenoid.set(Value.kReverse);
		else
			clawRotateSolenoid.set(Value.kForward);
		isClawRotated = !isClawRotated;
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
