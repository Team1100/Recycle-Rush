package org.team1100.commands.manipulator;

import org.team1100.Robot;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class UserElevatorCommand extends Command {

	public UserElevatorCommand() {
		requires(Elevator.getInstance());
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Elevator.getInstance().userLift();
		//SmartDashboard.putNumber("Encoder", Robot.elevator.getPosition());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Elevator.getInstance().stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
