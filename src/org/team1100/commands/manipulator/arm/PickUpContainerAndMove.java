package org.team1100.commands.manipulator.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpContainerAndMove extends CommandGroup{
	public PickUpContainerAndMove(){
		addSequential(new ClampArmClaw());
		addSequential(new MoveArm(-.7, 3));
	}
}
