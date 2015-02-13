package org.team1100.commands.manipulator;

import org.team1100.Robot;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ResetElevatorEncoderCommand extends Command {

	@Override
	protected void initialize() {
		requires(Elevator.getInstance());
		setInterruptible(true);
	}

	@Override
	protected void execute() {
		Elevator.getInstance().lift(-1);
	}

	@Override
	protected boolean isFinished() {
		return Elevator.getInstance().getBeamBreak();
	}

	@Override
	protected void end() {
		Elevator.getInstance().resetEncoder();
	}

	@Override
	protected void interrupted() {
		
	}

}
