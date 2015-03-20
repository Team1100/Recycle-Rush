package org.team1100.commands.manipulator.elevator;

import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ResetElevatorEncoder extends Command {

	public ResetElevatorEncoder() {
		requires(Elevator.getInstance());
	}

	@Override
	protected void initialize() {
		Elevator.getInstance().disable();
		// Elevator.getInstance().resetCounter();
		Elevator.getInstance().lift(-.6);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Elevator.getInstance().isBeamBroken();
	}

	@Override
	protected void end() {
		Elevator.getInstance().lift(0);
		Elevator.getInstance().resetEncoder();
		Elevator.getInstance().setSetpoint(0);
		Elevator.getInstance().enable();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
