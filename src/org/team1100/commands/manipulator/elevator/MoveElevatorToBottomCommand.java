package org.team1100.commands.manipulator.elevator;

import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveElevatorToBottomCommand extends Command {
	
	public MoveElevatorToBottomCommand(){
		requires(Elevator.getInstance());
	}
	
	@Override
	protected void initialize() {
		Elevator.getInstance().enable();
		Elevator.getInstance().setSetpoint(Elevator.BOTTOM_SETPOINT);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Elevator.getInstance().onTarget();
	}

	@Override
	protected void end() {
		Elevator.getInstance().disable();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
