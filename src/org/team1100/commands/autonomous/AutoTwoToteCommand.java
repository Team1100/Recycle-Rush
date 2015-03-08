package org.team1100.commands.autonomous;

import org.team1100.commands.drive.Drive;
import org.team1100.commands.drive.TurnRight;
import org.team1100.commands.manipulator.PickUpTote;
import org.team1100.commands.manipulator.arm.PickUpContainerAndMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTwoToteCommand extends CommandGroup {
	public AutoTwoToteCommand() {
		addParallel(new PickUpContainerAndMove());
		addSequential(new FirstTotePickUpCommand());
		addParallel(new Drive(.52, .6, 4));
		addSequential(new PickUpTote());
		addSequential(new TurnRight());
		addSequential(new Drive(.52, .6, 2));
	}

}
