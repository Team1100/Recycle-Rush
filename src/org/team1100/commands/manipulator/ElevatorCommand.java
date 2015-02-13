package org.team1100.commands.manipulator;

import org.team1100.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorCommand extends Command {

	public ElevatorCommand() {
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.elevator.userLift();
		//SmartDashboard.putNumber("Encoder", Robot.elevator.getPosition());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
