package org.team1100.commands.autonomous;

import org.team1100.commands.manipulator.PickUpTote;
import org.team1100.commands.manipulator.elevator.ResetElevatorEncoder;
import org.team1100.commands.manipulator.intake.CloseIntakeClaw;
import org.team1100.commands.manipulator.intake.RollInTote;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FirstTotePickUp extends CommandGroup {
	
	public FirstTotePickUp(){
		addSequential(new CloseIntakeClaw());
		addSequential(new RollInTote());
		addSequential(new ResetElevatorEncoder());
		addSequential(new PickUpTote(false));
	}
	
}
