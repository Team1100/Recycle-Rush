package org.team1100.commands.autonomous;

import org.team1100.commands.drive.Drive;
import org.team1100.commands.drive.Turn;
import org.team1100.commands.manipulator.PickUpTote;
import org.team1100.commands.manipulator.arm.PickUpContainerAndMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoToteAuto extends CommandGroup {
	public TwoToteAuto() {
		addParallel(new PickUpContainerAndMove());
		addSequential(new FirstTotePickUp());
		addParallel(new Drive(.52, .6, 4));
		addSequential(new PickUpTote());
		addSequential(new Turn(90));
		addSequential(new Drive(.52, .6, 2));
	}

}
