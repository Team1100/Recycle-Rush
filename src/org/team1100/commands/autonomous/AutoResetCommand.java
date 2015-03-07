package org.team1100.commands.autonomous;

import org.team1100.subsystems.Elevator;
import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class AutoResetCommand extends Command{

	private boolean isFinished = false;
	
	public AutoResetCommand(){
		requires(Elevator.getInstance());
		requires(Intake.getInstance());
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Elevator.getInstance().resetEncoder();
		Intake.getInstance().intakeOut();
		isFinished = true;
	}

	@Override
	protected boolean isFinished() {
		return isFinished;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
