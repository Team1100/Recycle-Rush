package org.team1100.commands.autonomous;

import org.team1100.commands.drive.DriveCommand;
import org.team1100.commands.manipulator.PickUpToteCommand;
import org.team1100.commands.manipulator.RollInToteCommand;
import org.team1100.commands.manipulator.elevator.MoveElevatorToDriveCommand;
import org.team1100.commands.manipulator.elevator.MoveElevatorToTopCommand;
import org.team1100.commands.manipulator.elevator.ResetElevatorEncoderCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoThreeToteCommand extends CommandGroup {
	public AutoThreeToteCommand() {
		/*addSequential(new AutoResetCommand());
		addParallel(new DriveCommand(.75,.8, 1.5));
		addSequential(new MoveElevatorToTopCommand());
		addSequential(new PickUpToteCommand());
		//addSequential(new RollInToteCommand());
		addSequential(new MoveElevatorToDriveCommand());
		addParallel(new DriveCommand(.6,.6, 1));
		addSequential(new PickUpToteCommand());
		addParallel(new DriveCommand(.6,.6, 1));
		addSequential(new PickUpToteCommand());
		addParallel(new DriveCommand(.6,.6, 1));*/
	}
}
