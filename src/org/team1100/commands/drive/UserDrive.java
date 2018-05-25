package org.team1100.commands.drive;

import org.team1100.OI;
import org.team1100.subsystems.DriveTrain;
import org.team1100.input.AttackThree;

import edu.wpi.first.wpilibj.command.Command;

public class UserDrive extends Command {

	public UserDrive() {
		requires(DriveTrain.getInstance());
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double leftValue = OI.getInstance().getLeftJoystick().getAxis(AttackThree.AttackThreeAxis.kY);
		double rightValue = OI.getInstance().getRightJoystick().getAxis(AttackThree.AttackThreeAxis.kY);

		DriveTrain.getInstance().driveTank(leftValue, rightValue);
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
