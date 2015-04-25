package org.team1100.commands.manipulator.elevator;

import org.team1100.OI;
import org.team1100.input.XboxController.XboxAxis;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class UserMoveElevator extends Command {

	private static double PID_COEFFICIENT = 300;
	private static final String KEY_NAME = "LiftPIDCoeffecient";

	public UserMoveElevator() {
		requires(Elevator.getInstance());
		Preferences.getInstance().putDouble(KEY_NAME, PID_COEFFICIENT);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		PID_COEFFICIENT = Preferences.getInstance().getDouble(KEY_NAME, PID_COEFFICIENT);
		double percent = -OI.getInstance().getXboxController().getAxis(XboxAxis.kYLeft);
		
		Elevator.getInstance().setSetpoint(Elevator.getInstance().getSetpoint() + PID_COEFFICIENT * percent);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
