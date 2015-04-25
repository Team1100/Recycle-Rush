package org.team1100.commands.manipulator.twitch;

import org.team1100.subsystems.Twitch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenTwitch extends Command {

	public OpenTwitch() {
		requires(Twitch.getInstance());
	}

	protected void initialize() {
		setTimeout(2);
	}

	protected void execute() {
			Twitch.getInstance().open();
	}

	protected boolean isFinished() {
		return isTimedOut() ||  Twitch.getInstance().isOut();
	}

	protected void end() {
		Twitch.getInstance().stop();
	}

	protected void interrupted() {
		end();
	}
	
}
