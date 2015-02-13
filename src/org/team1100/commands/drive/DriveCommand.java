package org.team1100.commands.drive;

import org.team1100.Robot;
import org.team1100.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {

	private double leftValue;
	private double rightValue;
	private double timeout;

	/**
	 * Drives the robot given the speed of each side and for how long
	 * 
	 * @param leftValue the value of the left side, from -1 to 1
	 * @param rightValue the value of the right side, from -1 to 1
	 * @param timeout how long the command should run, in seconds
	 */
	public DriveCommand(double leftValue, double rightValue, double timeout) {
		requires(DriveTrain.getInstance());
		this.leftValue = leftValue;
		this.rightValue = rightValue;
		this.timeout = timeout;
	}

	@Override
	protected void initialize() {
		setTimeout(timeout);
	}

	@Override
	protected void execute() {
		DriveTrain.getInstance().driveTank(leftValue, rightValue);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		DriveTrain.getInstance().stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
