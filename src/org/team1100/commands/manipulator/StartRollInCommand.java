package org.team1100.commands.manipulator;

import org.team1100.subsystems.Elevator;
import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class StartRollInCommand extends Command{
	
	public StartRollInCommand(){
		requires(Intake.getInstance());
		requires(Elevator.getInstance());
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Intake.getInstance().rollIn();
	}

	@Override
	protected boolean isFinished() {
		return Elevator.getInstance().isFrontBeamBroken(); 
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
