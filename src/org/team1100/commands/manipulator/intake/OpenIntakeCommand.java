package org.team1100.commands.manipulator.intake;

import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class OpenIntakeCommand extends Command {

	boolean isFinished;
	
	public OpenIntakeCommand() {
		requires(Intake.getInstance());
	}

	@Override
	protected void initialize() {
		isFinished = false;
	}

	@Override
	protected void execute() {
		Intake.getInstance().intakeOut();
		isFinished = true;
	}

	@Override
	protected boolean isFinished() {
		return isFinished;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
