package org.team1100.commands.manipulator;

import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleIntakeCommand extends Command{

	boolean isFinished;
	
	public ToggleIntakeCommand(){
		requires(Intake.getInstance());
		isFinished = false;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Intake.getInstance().toggleIntake();
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
