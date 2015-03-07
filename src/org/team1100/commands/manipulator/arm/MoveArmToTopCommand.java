package org.team1100.commands.manipulator.arm;

import org.team1100.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class MoveArmToTopCommand extends Command {

	public MoveArmToTopCommand() {
		requires(Arm.getInstance());
	}

	@Override
	protected void initialize() {
		Arm.getInstance().enable();
		Arm.getInstance().setSetpoint(Arm.TOP_SETPOINT);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Arm.getInstance().onTarget();
	}

	@Override
	protected void end() {
		Arm.getInstance().disable();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
