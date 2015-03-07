package org.team1100.commands.manipulator;

import org.team1100.subsystems.Elevator;
import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class RollInToteCommand extends Command {

	public RollInToteCommand(){
		requires(Intake.getInstance());
		requires(Elevator.getInstance());
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Intake.getInstance().rollIn();
	}

	@Override
	protected boolean isFinished() {
		return Elevator.getInstance().isToteInElevator();
	}

	@Override
	protected void end() {
		Intake.getInstance().stopIntake();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
