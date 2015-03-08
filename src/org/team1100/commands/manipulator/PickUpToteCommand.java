package org.team1100.commands.manipulator;

import org.team1100.commands.manipulator.elevator.MoveElevatorToBottomCommand;
import org.team1100.commands.manipulator.elevator.MoveElevatorToDriveCommand;
import org.team1100.commands.manipulator.elevator.MoveElevatorToTopCommand;
import org.team1100.commands.manipulator.intake.CloseIntakeCommand;
import org.team1100.commands.manipulator.intake.OpenIntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PickUpToteCommand extends CommandGroup {
	public PickUpToteCommand() {
		this(true);
	}

	public PickUpToteCommand(boolean moveUp) {

		addSequential(new OpenIntakeCommand());
		if (moveUp)
			addSequential(new MoveElevatorToTopCommand());
		addSequential(new StartRollInCommand());
		addSequential(new CloseIntakeCommand());
		addSequential(new RollInToteCommand());
		addSequential(new MoveElevatorToBottomCommand());
		addSequential(new WaitCommand(.25));
		addSequential(new MoveElevatorToDriveCommand());
	}
}
