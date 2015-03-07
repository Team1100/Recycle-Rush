package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.manipulator.intake.UserIntakeCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
	private boolean intakeClamped = false;

	private Intake() {
		victorLeft = new Victor(RobotMap.I_LEFT_MOTOR);
		victorRight = new Victor(RobotMap.I_RIGHT_MOTOR);
		solenoid = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.I_SOLENOID_A, RobotMap.I_SOLENOID_B);
		solenoid.set(Value.kOff);
		LiveWindow.addActuator("Intake", "Left Victor", victorLeft);
		LiveWindow.addActuator("Intake", "Right Victor", victorRight);
		LiveWindow.addSensor("Intake", "Solenoid", solenoid);
	}

	public void rollIn() {
		intake(1);
	}

	public void rollOut() {
		intake(-1);
	}

	public void stopIntake() {
		intake(0);
	}

	public void toggleIntake() {
		if (intakeClamped) {
			solenoid.set(Value.kReverse);
		} else {
			solenoid.set(Value.kForward);
		}
		intakeClamped = !intakeClamped;
	}

	public void intakeOut() {
		solenoid.set(Value.kReverse);
		intakeClamped = false;
	}

	public void intakeIn() {
		solenoid.set(Value.kForward);
		intakeClamped = true;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserIntakeCommand());
	}

	public void intake(double speed){
		victorLeft.set(speed);
		victorRight.set(speed);

	}

}
