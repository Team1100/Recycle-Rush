package org.team1100.commands.manipulator;

import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class RollOutCommand extends Command{

	public RollOutCommand(){
		requires(Intake.getInstance());
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
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
	
}
