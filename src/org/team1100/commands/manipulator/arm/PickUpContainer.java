package org.team1100.commands.manipulator.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpContainer extends CommandGroup{
	public PickUpContainer(){
		addSequential(new ClampArmClaw());
		addSequential(new MoveArm(-.7, 2.5));
	}
}
