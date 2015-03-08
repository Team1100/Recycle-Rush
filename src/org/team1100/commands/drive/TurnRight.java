package org.team1100.commands.drive;

import org.team1100.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class TurnRight extends Command {

	private double leftSpeed = 1;
	private double rightSpeed = 0;
	private double waitTime = 3;

	public TurnRight() {
		requires(DriveTrain.getInstance());
		Preferences.getInstance().putDouble("LeftSpeed", 1);
		Preferences.getInstance().putDouble("RightSpeed", 0);
		Preferences.getInstance().putDouble("WaitTime", waitTime);
	}

	@Override
	protected void initialize() {
		leftSpeed = Preferences.getInstance().getDouble("LeftSpeed", leftSpeed);
		rightSpeed = Preferences.getInstance().getDouble("RightSpeed", rightSpeed);
		waitTime = Preferences.getInstance().getDouble("WaitTime", waitTime);
		setTimeout(waitTime);
	}

	@Override
	protected void execute() {
		DriveTrain.getInstance().driveTank(leftSpeed, rightSpeed);
		
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
