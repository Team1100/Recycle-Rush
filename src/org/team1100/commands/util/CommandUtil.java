package org.team1100.commands.util;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandUtil {

	// TODO check to see if works
	public static CommandGroup newSeriesCommandGroup(Command ... commands){
		CommandGroup commandsInSeries = new CommandGroup();
		for (Command c : commands){
			commandsInSeries.addSequential(c);
		}
		return commandsInSeries;
	}
	
}
