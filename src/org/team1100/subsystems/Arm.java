package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.manipulator.arm.UserMoveArm;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem {

	public static Arm arm;

	public static Arm getInstance() {
		if (arm == null)
			arm = new Arm();
		return arm;
	}

	private Talon talonLeft;
	private Talon talonRight;
	private DoubleSolenoid gripperSolenoid;
	private DoubleSolenoid clawRotateSolenoid;
	private DigitalInput bannerSensor;

	private boolean isClawOpen;
	private boolean isClawParallel;

	private Arm() {
		talonLeft = new Talon(RobotMap.A_LEFT_MOTOR);
		talonRight = new Talon(RobotMap.A_RIGHT_MOTOR);

		gripperSolenoid = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.A_GRIPPER_SOLENOID_A, RobotMap.A_GRIPPER_SOLENOID_B);
		clawRotateSolenoid = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.A_CLAW_ROTATE_SOLENOID_A, RobotMap.A_CLAW_ROTATE_SOLENOID_B);
		bannerSensor = new DigitalInput(RobotMap.A_BANNER_SENSOR);

		openClaw();        // TODO Test whether this actually opens or closes the claw
		setClawParallel(); // TODO Test whether this actually rotates the claw to parallel

		LiveWindow.addActuator("Arm", "Right Talon", talonRight);
		LiveWindow.addActuator("Arm", "Left Talon", talonLeft);
		LiveWindow.addActuator("Arm", "Toggle Gripper", gripperSolenoid);
		LiveWindow.addActuator("Arm", "Rotate Claw", clawRotateSolenoid);
		LiveWindow.addSensor("Arm", "Banner Sensor", bannerSensor);
	}

	public void moveArm(double speed) {
		if (speed < 0 && isTooFarBack())
			speed = 0;
		talonLeft.set(-speed);
		talonRight.set(speed);
	}

	public boolean isTooFarBack() {
		return !bannerSensor.get();
	}

	public void toggleClawClamped() {
		if (isClawOpen)
			closeClaw();
		else
			openClaw();
	}

	public void openClaw() {
		gripperSolenoid.set(Value.kForward);
		isClawOpen = true;
	}

	public void closeClaw() {
		gripperSolenoid.set(Value.kReverse);
		isClawOpen = false;

	}

	public void toggleRotateClaw() {
		if (isClawParallel)
			setClawPerpendicular();
		else
			setClawParallel();
	}
	
	public void setClawParallel(){
		clawRotateSolenoid.set(Value.kReverse);
		isClawParallel = true;
	}
	
	public void setClawPerpendicular(){
		clawRotateSolenoid.set(Value.kForward);
		isClawParallel = false;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserMoveArm());
	}

	public void log() {
		SmartDashboard.putBoolean("Arm Claw Open", isClawOpen);
	}
}
