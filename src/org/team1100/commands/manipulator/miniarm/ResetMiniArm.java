package org.team1100.commands.manipulator.miniarm;

import org.team1100.subsystems.MiniArm;

import edu.wpi.first.wpilibj.command.Command;

public class ResetMiniArm extends Command{

	public ResetMiniArm(){
		requires(MiniArm.getInstance());
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		MiniArm.getInstance().setOut(false);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
