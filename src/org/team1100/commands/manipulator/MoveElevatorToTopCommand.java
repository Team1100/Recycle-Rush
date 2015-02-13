package org.team1100.commands.manipulator;

import org.team1100.Robot;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToTopCommand extends Command {
	
	@Override
	protected void initialize() {
		requires(Elevator.getInstance());
		Elevator.getInstance().goToTop();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Elevator.getInstance().onTarget();
	}

	@Override
	protected void end() {
		Elevator.getInstance().disable();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
