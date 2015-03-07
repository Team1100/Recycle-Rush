package org.team1100.commands.manipulator.elevator;

import org.team1100.OI;
import org.team1100.input.XboxController.XboxAxis;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class UserElevatorCommand extends Command {

	private static double SPEED_PERCENT = 0.8;
	private static final String KEY_NAME = "Lift_Speed_Percent";

	public UserElevatorCommand() {
		requires(Elevator.getInstance());
	}

	@Override
	protected void initialize() {
		Preferences.getInstance().putDouble(KEY_NAME, SPEED_PERCENT);
	}

	@Override
	protected void execute() {
		SPEED_PERCENT = Preferences.getInstance().getDouble(KEY_NAME, SPEED_PERCENT);
		double speed = -OI.getInstance().getXboxController().getAxis(XboxAxis.kYLeft) * SPEED_PERCENT;;

		Elevator.getInstance().lift(speed);
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
		end();
	}

}
