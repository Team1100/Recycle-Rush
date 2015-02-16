package org.team1100.commands.manipulator.arm;

import org.team1100.OI;
import org.team1100.input.XboxController.XboxAxis;
import org.team1100.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class UserArmCommand extends Command {

	public UserArmCommand() {
		requires(Arm.getInstatnce());
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double speed = OI.getInstance().getXboxController().getAxis(XboxAxis.kYLeft);
		Arm.getInstatnce().moveArm(speed);
	}

	@Override
	protected boolean isFinished() {
		return false;
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
