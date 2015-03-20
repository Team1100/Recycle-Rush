package org.team1100.commands.manipulator.intake;

import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class RollInTote extends Command {

	public RollInTote(){
		requires(Intake.getInstance());
	}
	
	@Override
	protected void initialize() {
		Intake.getInstance().rollIn();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Intake.getInstance().isToteInElevator();
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
