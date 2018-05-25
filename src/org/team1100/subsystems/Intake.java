package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.manipulator.intake.UserSpinIntake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem {

	private static Intake intake;

	public static Intake getInstance() {
		if (intake == null) {
			intake = new Intake();
		}
		return intake;
	}

	private Victor victorLeft;
	private Victor victorRight;
	private DoubleSolenoid solenoid;
	private DigitalInput bannerSensorBack;
	private DigitalInput bannerSensorFront;

	private boolean isClawClosed;

	private Intake() {
		victorLeft = new Victor(RobotMap.I_LEFT_MOTOR);
		victorRight = new Victor(RobotMap.I_RIGHT_MOTOR);
		solenoid = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.I_SOLENOID_A, RobotMap.I_SOLENOID_B);
		bannerSensorBack = new DigitalInput(RobotMap.E_BANNER_SENSOR_BACK);
		bannerSensorFront = new DigitalInput(RobotMap.E_BANNER_SENSOR_FRONT);

		solenoid.set(Value.kOff);
		isClawClosed = false;
	}

	public boolean isToteInElevator() {
		return !bannerSensorBack.get();
	}

	public boolean isToteInIntake() {
		return !bannerSensorFront.get();
	}

	public void rollIn() {
		spin(1);
	}

	public void rollOut() {
		spin(-1);
	}

	public void stopIntake() {
		spin(0);
	}

	public void toggleIntake() {
		if (isClawClosed)
			openIntake();
		else
			closeIntake();
	}

	public void openIntake() {
		solenoid.set(Value.kReverse);
		isClawClosed = false;

	}

	public void closeIntake() {
		solenoid.set(Value.kForward);
		isClawClosed = true;

	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserSpinIntake());
	}

	public void spin(double speed) {
		victorLeft.set(speed);
		victorRight.set(speed);
	}

	public void log() {
		SmartDashboard.putBoolean("Tote In", isToteInElevator());
	}
}