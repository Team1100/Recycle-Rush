package org.team1100.commands.manipulator;

import org.team1100.commands.manipulator.elevator.MoveElevatorToBottomCommand;
import org.team1100.commands.manipulator.elevator.MoveElevatorToDriveCommand;
import org.team1100.commands.manipulator.elevator.MoveElevatorToTopCommand;
import org.team1100.commands.manipulator.intake.CloseIntakeCommand;
import org.team1100.commands.manipulator.intake.OpenIntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpToteCommand extends CommandGroup {
	public PickUpToteCommand() {
		addSequential(new OpenIntakeCommand());
		addSequential(new MoveElevatorToTopCommand());
		addSequential(new StartRollInCommand());
		addSequential(new CloseIntakeCommand());
		addSequential(new RollInToteCommand());
		addSequential(new MoveElevatorToBottomCommand());
		addSequential(new MoveElevatorToDriveCommand());
	}
}
