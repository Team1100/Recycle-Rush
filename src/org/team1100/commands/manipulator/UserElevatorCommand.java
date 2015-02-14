package org.team1100.commands.manipulator;

import org.team1100.OI;
import org.team1100.input.XboxController;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class UserElevatorCommand extends Command {

	private static double SPEED_PERCENT = 0.5;

	public UserElevatorCommand() {
		requires(Elevator.getInstance());
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double speed = 0;
		double leftSpeed = OI.getInstance().getXboxController()
				.getAxis(XboxController.XboxAxis.kLeftTrigger);
		double rightSpeed = OI.getInstance().getXboxController()
				.getAxis(XboxController.XboxAxis.kRightTrigger);

		if (leftSpeed != 0)
			speed = leftSpeed * SPEED_PERCENT;
		else if (rightSpeed != 0)
			speed = -rightSpeed * SPEED_PERCENT;

		Elevator.getInstance().lift(speed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Elevator.getInstance().stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
