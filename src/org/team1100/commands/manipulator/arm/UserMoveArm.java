package org.team1100.commands.manipulator.arm;

import org.team1100.OI;
import org.team1100.input.XboxController.XboxAxis;
import org.team1100.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class UserMoveArm extends Command {

	public UserMoveArm() {
		requires(Arm.getInstance());
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double speed = OI.getInstance().getXboxController().getAxis(XboxAxis.kYRight);
		Arm.getInstance().moveArm(speed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Arm.getInstance().moveArm(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
