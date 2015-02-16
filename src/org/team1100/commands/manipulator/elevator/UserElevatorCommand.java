package org.team1100.commands.manipulator.elevator;

import org.team1100.OI;
import org.team1100.input.XboxController;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UserElevatorCommand extends Command {

	private static double SPEED_PERCENT = 0.5;
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
		double speed = 0;
		//TODO implement launchpad control here
		double leftSpeed = OI.getInstance().getXboxController()
				.getAxis(XboxController.XboxAxis.kLeftTrigger);
		double rightSpeed = OI.getInstance().getXboxController()
				.getAxis(XboxController.XboxAxis.kRightTrigger);

		if (leftSpeed != 0)
			speed = -leftSpeed * SPEED_PERCENT;
		else if (rightSpeed != 0)
			speed = rightSpeed * SPEED_PERCENT;

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
