package org.team1100;

import org.team1100.commands.autonomous.AutonomousCommand;
import org.team1100.commands.manipulator.ResetElevatorEncoderCommand;
import org.team1100.commands.util.LogFileCommand;
import org.team1100.input.MicrosoftCamera;
import org.team1100.subsystems.DriveTrain;
import org.team1100.subsystems.Elevator;
import org.team1100.subsystems.Intake;

import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
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

	public static LogFileCommand logFile;

	private AutonomousCommand autoCommand;
	private boolean isCameraConnected = true;

	public void robotInit() {
		OI.getInstance();
		try {
			MicrosoftCamera.getInstance().start();
		} catch (VisionException ve) {
			isCameraConnected = false;
		}
		SmartDashboard.putBoolean("Camera Connected: ", isCameraConnected);

		SmartDashboard.putData(DriveTrain.getInstance());
		SmartDashboard.putData(Elevator.getInstance());
		SmartDashboard.putData(Intake.getInstance());

		SmartDashboard.putData(new ResetElevatorEncoderCommand());

		SmartDashboard.putData(Scheduler.getInstance());
		
		autoCommand = new AutonomousCommand();
		logFile = new LogFileCommand();
	}

	private void log() {
		double l = 4;
		Preferences.getInstance().putDouble("Test", 4);
		Preferences.getInstance().getDouble("Test", l);
	}

	public void autonomousInit() {
		autoCommand.start();
		// logFile.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void teleopInit() {
		/*
		 * if (!logFile.isRunning()) logFile.start();
		 */
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
	}

	public void disabledInit() {
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		log();
	}
}
