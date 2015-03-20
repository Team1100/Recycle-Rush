package org.team1100.commands.autonomous;

import org.team1100.commands.drive.Drive;
import org.team1100.commands.drive.Turn;
import org.team1100.commands.manipulator.arm.PickUpContainerAndMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneToteAuto extends CommandGroup {

	public OneToteAuto() {
		addSequential(new PickUpContainerAndMove());
		addParallel(new FirstTotePickUp());
		addSequential(new Turn(90));
		addSequential(new Drive(.7,.7,2));
	}

}
