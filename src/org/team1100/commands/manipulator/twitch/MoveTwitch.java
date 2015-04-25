package org.team1100.commands.manipulator.twitch;

import org.team1100.subsystems.Twitch;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class MoveTwitch extends Command {

	private String speedKey = "MiniArmSpeed";
	private double SPEED = .5;

	private int sign;

	public MoveTwitch(boolean foward) {
		requires(Twitch.getInstance());
		sign = (foward) ? 1 : -1;

		Preferences.getInstance().putDouble(speedKey, SPEED);
	}

	@Override
	protected void initialize() {
		SPEED = Preferences.getInstance().getDouble(speedKey, SPEED);
	}

	@Override
	protected void execute() {
		Twitch.getInstance().move(sign * .5);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Twitch.getInstance().stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
