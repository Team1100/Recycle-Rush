package org.team1100.commands.manipulator;

import org.team1100.commands.drive.DriveCommand;
import org.team1100.commands.manipulator.arm.MoveArmToTopCommand;
import org.team1100.commands.manipulator.arm.ScoreContainerCommand;
import org.team1100.commands.manipulator.elevator.MoveElevatorToBottomCommand;
import org.team1100.commands.manipulator.intake.OpenIntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreTotesCommand extends CommandGroup{
	public ScoreTotesCommand(){
		addSequential(new MoveArmToTopCommand());
		addSequential(new MoveElevatorToBottomCommand());
		addSequential(new OpenIntakeCommand());
		addSequential(new RollOutToteCommand());
		addParallel(new DriveCommand(-.5, -.5, 1));
		addParallel(new ScoreContainerCommand());
	}
}
