package org.team1100.commands.autonomous;

import org.team1100.commands.manipulator.PickUpTote;
import org.team1100.commands.manipulator.elevator.SetElevatorHeight;
import org.team1100.commands.manipulator.intake.CloseIntakeClaw;
import org.team1100.commands.manipulator.intake.OpenIntakeClaw;
import org.team1100.commands.manipulator.intake.RollAndClampTote;
import org.team1100.commands.manipulator.intake.RollInTote;
import org.team1100.commands.manipulator.miniarm.OpenMiniArm;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PickUpThreeTotes extends CommandGroup {

	public PickUpThreeTotes() {
		addSequential(new FirstTotePickUp());

		// PickUpTote Command with OpenMiniArm injected
		addSequential(new OpenIntakeClaw());
		addSequential(new SetElevatorHeight(Elevator.TOP));
		addSequential(new RollAndClampTote());
		addSequential(new CloseIntakeClaw());

		// Injected command
		addParallel(new MiniArmWait());
		
		addSequential(new RollInTote());
		addSequential(new SetElevatorHeight(Elevator.BOTTOM));
		addSequential(new WaitCommand(.25));
		addParallel(new OpenIntakeClaw());
		addSequential(new SetElevatorHeight(Elevator.TOP));

		addSequential(new PickUpTote());
	}

}
