package org.team1100.commands.manipulator;

import org.team1100.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToBottomCommand extends Command {
	
	@Override
	protected void initialize() {
		requires(Robot.elevator);
		Robot.elevator.goToBottom();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Robot.elevator.onTarget();
	}

	@Override
	protected void end() {
		Robot.elevator.disable();
	}

	@Override
	protected void interrupted() {
		end();
	}

}