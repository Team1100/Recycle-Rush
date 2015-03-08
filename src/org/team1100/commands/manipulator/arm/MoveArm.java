package org.team1100.commands.manipulator.arm;

import org.team1100.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class MoveArm extends Command {

	private double speed;
	private double timeout;

	/**
	 * Drives the robot given the speed of each side and for how long
	 * 
	 * @param leftValue the value of the left side, from -1 to 1
	 * @param rightValue the value of the right side, from -1 to 1
	 * @param timeout how long the command should run, in seconds
	 */
	public MoveArm(double speed, double timeout) {
		requires(Arm.getInstance());
		this.speed = speed;
		this.timeout = timeout;
	}

	@Override
	protected void initialize() {
		setTimeout(timeout);
		Arm.getInstance().moveArm(speed);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
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
