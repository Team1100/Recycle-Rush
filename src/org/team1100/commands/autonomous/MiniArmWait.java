package org.team1100.commands.autonomous;

import org.team1100.commands.manipulator.miniarm.OpenMiniArm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class MiniArmWait extends CommandGroup {
	public MiniArmWait(){
		addSequential(new WaitCommand(.5));
		addSequential(new OpenMiniArm());
	}
}
