package org.team1100.subsystems;

import org.team1100.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	private static Intake intake = null;

	public static Intake getInstance() {
		if (intake == null) {
			intake = new Intake();
		}
		return intake;
	}

	private Victor victorLeft;
	private Victor victorRight;
	private Solenoid solenoidLeft;
	private Solenoid solenoidRight;
	private boolean intakeClamped = false;

	private Intake() {
		victorLeft = new Victor(RobotMap.M_INTAKE_LEFT_MOTOR);
		victorRight = new Victor(RobotMap.M_INTAKE_RIGHT_MOTOR);
		solenoidLeft = new Solenoid(RobotMap.M_INTAKE_LEFT_GRIP_CYLINDER);
		solenoidRight = new Solenoid(RobotMap.M_INTAKE_RIGHT_GRIP_CYLINDER);
	}

	public void rollIn() {
		victorLeft.set(1);
		victorRight.set(-1);
	}

	public void rollOut() {
		victorLeft.set(-1);
		victorRight.set(1);
	}

	public void toggleIntake() {
		if (intakeClamped) {
			solenoidLeft.set(false);
			solenoidRight.set(false);
		} else {
			solenoidLeft.set(true);
			solenoidRight.set(true);
		}
		intakeClamped = !intakeClamped;
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
