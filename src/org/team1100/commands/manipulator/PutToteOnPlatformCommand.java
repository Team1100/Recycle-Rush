package org.team1100.commands.manipulator;

import org.team1100.commands.manipulator.elevator.MoveElevatorToPlatformCommand;
import org.team1100.commands.manipulator.elevator.PushToteCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PutToteOnPlatformCommand extends CommandGroup{
	public PutToteOnPlatformCommand(){
		addSequential(new MoveElevatorToPlatformCommand());
		addSequential(new PushToteCommand());
		addSequential(new RollOutToteCommand());
	}
}
