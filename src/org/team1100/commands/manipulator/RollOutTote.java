package org.team1100.commands.manipulator;

import org.team1100.subsystems.Elevator;
import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class RollOutTote extends Command {

	public RollOutTote() {
		requires(Intake.getInstance());
		requires(Elevator.getInstance());
	}

	@Override
	protected void initialize() {
		Intake.getInstance().rollOut();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return !Elevator.getInstance().isToteInIntake();
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}

}
