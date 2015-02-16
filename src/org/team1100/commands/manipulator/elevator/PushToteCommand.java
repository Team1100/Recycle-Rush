package org.team1100.commands.manipulator.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


public class PushToteCommand extends CommandGroup {
	public PushToteCommand() {
		addSequential(new PushOutPistonCommand());
		addSequential(new WaitCommand(.5));
		addSequential(new PullInPistonCommand());
	}
}
