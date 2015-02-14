package org.team1100.commands.manipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpToteCommand extends CommandGroup {
	public PickUpToteCommand() {
		addSequential(new RollInToteCommand());
		addSequential(new MoveElevatorToBottomCommand());
		addSequential(new MoveElevatorToTopCommand());
	}
}
