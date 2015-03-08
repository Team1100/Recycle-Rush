package org.team1100.commands.autonomous;

import org.team1100.commands.manipulator.PickUpToteCommand;
import org.team1100.commands.manipulator.RollInToteCommand;
import org.team1100.commands.manipulator.elevator.ResetElevatorEncoderCommand;
import org.team1100.commands.manipulator.intake.CloseIntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FirstTotePickUpCommand extends CommandGroup {
	
	public FirstTotePickUpCommand(){
		addSequential(new CloseIntakeCommand());
		addSequential(new RollInToteCommand());
		addSequential(new ResetElevatorEncoderCommand());
		addSequential(new PickUpToteCommand(false));
	}
	
}
