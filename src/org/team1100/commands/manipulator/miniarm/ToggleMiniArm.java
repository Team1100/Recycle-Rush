package org.team1100.commands.manipulator.miniarm;

import org.team1100.subsystems.MiniArm;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleMiniArm extends Command {
	public ToggleMiniArm() {
		requires(MiniArm.getInstance());
	}

	@Override
	protected void initialize() {
		setTimeout(1);
	}

	@Override
	protected void execute() {
		if (MiniArm.getInstance().isOpen())
			MiniArm.getInstance().close();
		else
			MiniArm.getInstance().open();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		MiniArm.getInstance().setOut(!MiniArm.getInstance().isOpen());
		MiniArm.getInstance().stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
