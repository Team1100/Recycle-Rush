package org.team1100.commands.manipulator.elevator;

import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class ResetElevatorEncoderCommand extends Command {
	
	private double RESET_SPEED = -.5;
	
	public ResetElevatorEncoderCommand(){
		requires(Elevator.getInstance());
		setInterruptible(false);
	}
	
	@Override
	protected void initialize() {	
	}

	@Override
	protected void execute() {
;		Elevator.getInstance().lift(-.6);
	}

	@Override
	protected boolean isFinished() {
		return Elevator.getInstance().isBeamBroken();
	}

	@Override
	protected void end() {
		Elevator.getInstance().resetEncoder();
	}

	@Override
	protected void interrupted() {
		//This will never be called
	}

}
