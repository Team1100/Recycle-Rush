package org.team1100.commands.autonomous;

import org.team1100.commands.drive.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoThreeToteDrivingCommand extends CommandGroup {
	public AutoThreeToteDrivingCommand() {
		addSequential(new Drive(.5, .6, 7.2));
		addSequential(new Drive(1, 0, 1.2));
		addSequential(new Drive(1, 1, .4));
		addSequential(new Drive(1, 0, .8));
		addSequential(new Drive(.8, .8, 2));
		addSequential(new WaitCommand(.5));
		addSequential(new Drive(-.8, -.8, 1));
	}
}
