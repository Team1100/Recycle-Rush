package org.team1100.commands.autonomous;

import org.team1100.commands.drive.DriveCommand;
import org.team1100.commands.drive.TurnRightCommand;
import org.team1100.commands.manipulator.PickUpToteCommand;
import org.team1100.commands.manipulator.arm.PickUpContainerCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTwoToteCommand extends CommandGroup {
	public AutoTwoToteCommand() {
		addParallel(new PickUpContainerCommand());
		addSequential(new FirstTotePickUpCommand());
		addParallel(new DriveCommand(.52, .6, 4));
		addSequential(new PickUpToteCommand());
		addSequential(new TurnRightCommand());
		addSequential(new DriveCommand(.52, .6, 2));
	}

}
