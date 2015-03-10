package org.team1100.commands.manipulator;

import org.team1100.commands.manipulator.elevator.SetElevatorHeight;
import org.team1100.commands.manipulator.intake.CloseIntakeClaw;
import org.team1100.commands.manipulator.intake.OpenIntakeClaw;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PickUpTote extends CommandGroup {
	public PickUpTote() {
		this(true);
	}

	public PickUpTote(boolean moveUp) {
		addSequential(new OpenIntakeClaw());
		if (moveUp)
			addSequential(new SetElevatorHeight(Elevator.TOP));
		addSequential(new RollAndClampTote());
		addSequential(new CloseIntakeClaw());
		addSequential(new RollInTote());
		addSequential(new SetElevatorHeight(Elevator.BOTTOM));
		addSequential(new WaitCommand(.25));
		addParallel(new OpenIntakeClaw());
		addSequential(new SetElevatorHeight(Elevator.TOP));
	}
}
