package org.team1100.commands.autonomous;

import org.team1100.commands.drive.Drive;
import org.team1100.commands.drive.TurnRight;
import org.team1100.commands.manipulator.arm.PickUpContainer;
import org.team1100.commands.manipulator.elevator.SetElevatorHeight;
import org.team1100.commands.manipulator.intake.RollOutTote;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ThreeToteAuto extends CommandGroup {
	public ThreeToteAuto() {
		addParallel(new PickUpContainer()); 		// TODO Replace with series util
		addParallel(new DriveAndTwitch()); 			// TODO Replace with series util
		// addParallel(new Drive(.6,.7,6));
		addSequential(new PickUpThreeTotes()); 		// TODO Replace with series util
		addSequential(new Drive(-.75, -.8, .5));
		addSequential(new WaitCommand(.5));
		addSequential(new TurnRight(90));
		addParallel(new SetElevatorHeight(Elevator.BOTTOM));
		addSequential(new Drive(.75, .8, 1));
		addParallel(new RollOutTote());
		addSequential(new Drive(-.75, -.8, 1));
	}
}
