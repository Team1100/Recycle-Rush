package org.team1100.commands.manipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpToteCommand extends CommandGroup {
	public PickUpToteCommand(){
		addSequential(new MoveElevatorToBottomCommand());
		addSequential(new MoveElevatorToTopCommand());
	}
}
