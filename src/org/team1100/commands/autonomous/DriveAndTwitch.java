package org.team1100.commands.autonomous;

import org.team1100.commands.drive.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndTwitch extends CommandGroup{
	public DriveAndTwitch(){
		addSequential(new Drive(.6,.7,3));
		addSequential(new MiniArmWait());
		addSequential(new Drive(.6,.7,3));
	}
}
