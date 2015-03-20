package org.team1100.commands.manipulator;

import org.team1100.commands.drive.Drive;
import org.team1100.commands.manipulator.arm.SetArmPosition;
import org.team1100.commands.manipulator.elevator.SetElevatorHeight;
import org.team1100.commands.manipulator.intake.OpenIntakeClaw;
import org.team1100.commands.manipulator.intake.RollOutTote;
import org.team1100.subsystems.Arm;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreTotes extends CommandGroup {
	public ScoreTotes() {
		addSequential(new SetArmPosition(Arm.TOP_SETPOINT));
		addSequential(new SetElevatorHeight(Elevator.BOTTOM));
		addSequential(new OpenIntakeClaw());
		addSequential(new RollOutTote());
		addParallel(new Drive(-.5, -.5, 1));
		addParallel(new SetArmPosition(Arm.SCORING_SETPOINT));
	}
}
