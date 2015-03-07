package org.team1100.commands.manipulator.intake;

import org.team1100.OI;
import org.team1100.input.XboxController.XboxAxis;
import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class UserIntakeCommand extends Command{

	public UserIntakeCommand(){
		requires(Intake.getInstance());
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		double speedLeft = -OI.getInstance().getXboxController().getAxis(XboxAxis.kLeftTrigger);
		double speedRight = OI.getInstance().getXboxController().getAxis(XboxAxis.kRightTrigger);
		Intake.getInstance().intake(speedLeft + speedRight);
		
	}

	@Override
	protected boolean isFinished() {
		return false;
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
