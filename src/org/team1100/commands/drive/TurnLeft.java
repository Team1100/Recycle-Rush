package org.team1100.commands.drive;

import org.team1100.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class TurnLeft extends Command {

	private double leftSpeed = -1;
	private double rightSpeed = .8;
	private double waitTime = 2.2;

	public TurnLeft() {
		requires(DriveTrain.getInstance());
		Preferences.getInstance().putDouble("LeftSpeed", leftSpeed);
		Preferences.getInstance().putDouble("RightSpeed", rightSpeed);
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
