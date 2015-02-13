package org.team1100;

import org.team1100.commands.AutonomousCommand;
import org.team1100.commands.manipulator.ResetElevatorEncoderCommand;
import org.team1100.subsystems.DriveTrain;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private AutonomousCommand autoCommand;

	public void robotInit() {
		OI.getInstance();
		// LogitechCamera.getInstance().start();
		SmartDashboard.putData(DriveTrain.getInstance());
		SmartDashboard.putData(Elevator.getInstance());
		
		SmartDashboard.putData(new ResetElevatorEncoderCommand());

		autoCommand = new AutonomousCommand();
	}

	public void autonomousInit() {
		autoCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void teleopInit() {
		autoCommand.cancel();
		if (!Elevator.getInstance().isEncoderReset())
			new ResetElevatorEncoderCommand().start();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void testPeriodic() {
		LiveWindow.run();
		log();
	}

	public void disabledInit() {
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	private void log() {

	}
}
