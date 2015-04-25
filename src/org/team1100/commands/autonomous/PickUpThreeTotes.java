package org.team1100.commands.autonomous;

import org.team1100.commands.manipulator.PickUpTote;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpThreeTotes extends CommandGroup {

	public PickUpThreeTotes() {
		addSequential(new FirstTotePickUp());
		addSequential(new PickUpTote());
		addSequential(new PickUpTote());
	}
}