package org.team1100.commands.manipulator.arm;

import org.team1100.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClawRotateCommand extends Command{

	private boolean isFinished = false;
	
	public ToggleClawRotateCommand(){
		requires(Arm.getInstance());
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Arm.getInstance().toggleRotateClaw();
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
