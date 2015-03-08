package org.team1100.commands.manipulator.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpContainerCommand extends CommandGroup{
	public PickUpContainerCommand(){
		addSequential(new ClampArmGripperCommand());
		addSequential(new ArmCommand(-.7, 2.3));
	}
}
