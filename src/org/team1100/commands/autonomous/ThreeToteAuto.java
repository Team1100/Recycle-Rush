package org.team1100.commands.autonomous;

import org.team1100.commands.drive.Drive;
import org.team1100.commands.drive.Turn;
import org.team1100.commands.manipulator.arm.PickUpContainerAndMove;
import org.team1100.commands.manipulator.elevator.SetElevatorHeight;
import org.team1100.commands.manipulator.intake.RollOutTote;
import org.team1100.commands.manipulator.miniarm.ResetMiniArm;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeToteAuto extends CommandGroup {
	public ThreeToteAuto() {
		addParallel(new ResetMiniArm());
		addParallel(new PickUpContainerAndMove());
		addParallel(new Drive(.52, .6, 9));
		addSequential(new PickUpThreeTotes());
		addSequential(new Turn(90));
		addParallel(new Drive(.75, .8, 3));
		addSequential(new SetElevatorHeight(Elevator.BOTTOM));
		addParallel(new RollOutTote());
		addSequential(new Drive(-.75, -.8, 1));
	}
}
