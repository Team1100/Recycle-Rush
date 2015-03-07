package org.team1100.commands.autonomous;

import org.team1100.commands.drive.DriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoThreeToteDrivingCommand extends CommandGroup {
	public AutoThreeToteDrivingCommand() {
		addSequential(new DriveCommand(.5, .6, 7.2));
		addSequential(new DriveCommand(1, 0, 1.2));
		addSequential(new DriveCommand(1, 1, .4));
		addSequential(new DriveCommand(1, 0, .8));
		addSequential(new DriveCommand(.8, .8, 2));
		addSequential(new WaitCommand(.5));
		addSequential(new DriveCommand(-.8, -.8, 1));
	}
}
