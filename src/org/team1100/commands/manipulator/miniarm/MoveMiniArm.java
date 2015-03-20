package org.team1100.commands.manipulator.miniarm;

import org.team1100.subsystems.MiniArm;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class MoveMiniArm extends Command {

	private String speedKey = "MiniArmSpeed";
	private double SPEED = .5;

	private int sign;

	public MoveMiniArm(boolean foward) {
		requires(MiniArm.getInstance());
		sign = (foward) ? 1 : -1;

		Preferences.getInstance().putDouble(speedKey, SPEED);
	}

	@Override
	protected void initialize() {
		SPEED = Preferences.getInstance().getDouble(speedKey, SPEED);
	}

	@Override
	protected void execute() {
		MiniArm.getInstance().spin(sign * .5);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		MiniArm.getInstance().stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
