package org.team1100.commands.manipulator.arm;

import org.team1100.OI;
import org.team1100.input.XboxController.XboxAxis;
import org.team1100.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class UserArmCommand extends Command {

	public UserArmCommand() {
		requires(Arm.getInstance());
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		int pov = OI.getInstance().getXboxController().getPOV(0);
		if (pov != -1)
			Arm.getInstance().enable();
		if (pov == 270) {
			// setpoint 1
		} else if (pov == 0) {
			// setpoint 2
		} else if (pov == 90) {
			// setpoint 3
		} else {
			Arm.getInstance().disable();
			double speed = OI.getInstance().getXboxController().getAxis(XboxAxis.kYRight);
			Arm.getInstance().moveArm(speed);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
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
