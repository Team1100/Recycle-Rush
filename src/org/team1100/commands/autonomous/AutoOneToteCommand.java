package org.team1100.commands.autonomous;

import org.team1100.commands.drive.DriveCommand;
import org.team1100.commands.drive.TurnRightCommand;
import org.team1100.commands.manipulator.arm.PickUpContainerCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoOneToteCommand extends CommandGroup {

	public AutoOneToteCommand() {
		addParallel(new PickUpContainerCommand());
		addParallel(new FirstTotePickUpCommand());
		addSequential(new TurnRightCommand());
		addSequential(new DriveCommand(.7,.7,2));
	}

}
