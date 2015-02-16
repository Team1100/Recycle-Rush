package org.team1100.commands.manipulator;

import org.team1100.commands.manipulator.elevator.MoveElevatorToBottomCommand;
import org.team1100.commands.manipulator.elevator.MoveElevatorToPlatformCommand;
import org.team1100.commands.manipulator.elevator.MoveElevatorToTopCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpToteCommand extends CommandGroup {
	public PickUpToteCommand() {
		addSequential(new MoveElevatorToTopCommand());
		addSequential(new RollInToteCommand());
		addSequential(new MoveElevatorToBottomCommand());
		addSequential(new MoveElevatorToPlatformCommand());
	}
}
