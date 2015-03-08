package org.team1100.commands.manipulator.arm;

import org.team1100.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class ClampArmClaw extends Command {

	public ClampArmClaw() {
		requires(Arm.getInstance());
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Arm.getInstance().clampGripper();
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
