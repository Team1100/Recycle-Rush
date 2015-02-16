package org.team1100.commands.manipulator.elevator;

import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToTopCommand extends Command {
	
	public MoveElevatorToTopCommand(){
		requires(Elevator.getInstance());
	}
	
	@Override
	protected void initialize() {
		Elevator.getInstance().enable();
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
