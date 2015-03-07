package org.team1100.commands.manipulator.elevator;

import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class WaitForToteCommand extends Command {

	public WaitForToteCommand() {
		requires(Elevator.getInstance());
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Elevator.getInstance().isToteInElevator();
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}

}
