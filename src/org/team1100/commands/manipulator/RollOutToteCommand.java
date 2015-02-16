package org.team1100.commands.manipulator;

import org.team1100.subsystems.Elevator;
import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class RollOutToteCommand extends Command {

	public RollOutToteCommand() {
		requires(Intake.getInstance());
		requires(Elevator.getInstance());
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Intake.getInstance().rollOut();
	}

	@Override
	protected boolean isFinished() {
		return Elevator.getInstance().isToteOutOfElevator();
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}

}
