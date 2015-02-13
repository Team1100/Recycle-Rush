package org.team1100.commands;

import org.team1100.commands.drive.DriveCommand;
import org.team1100.commands.manipulator.PickUpToteCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
	public AutonomousCommand() {
		addSequential(new PickUpToteCommand());
		addSequential(new DriveCommand(.8,.8, 2));
		addSequential(new PickUpToteCommand());
		addSequential(new DriveCommand(.8,.8, 2));
	}
}
