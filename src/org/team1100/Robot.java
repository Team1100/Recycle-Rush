package org.team1100;

import org.team1100.commands.autonomous.AutoOneToteCommand;
import org.team1100.commands.autonomous.AutoTwoToteCommand;
import org.team1100.commands.drive.DriveCommand;
import org.team1100.commands.drive.TurnRightCommand;
import org.team1100.commands.util.LogFileCommand;
import org.team1100.subsystems.Arm;
import org.team1100.subsystems.DriveTrain;
import org.team1100.subsystems.Elevator;
import org.team1100.subsystems.Intake;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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

	private Command autoCommand;
	private boolean isCameraConnected = true;

	private SendableChooser autoChooser;

	public void robotInit() {
		OI.getInstance();
		/*
		 * try {
		 * CameraServer.getInstance().startAutomaticCapture(RobotMap.CAMERA_NAME
		 * ); //MicrosoftCamera.getInstance().start(); } catch (VisionException
		 * ve) { isCameraConnected = false; } catch (Exception e) {
		 * e.printStackTrace(System.err); isCameraConnected = false;
		 * 
		 * } SmartDashboard.putBoolean("Camera Connected: ", isCameraConnected);
		 */

		SmartDashboard.putData(DriveTrain.getInstance());
		SmartDashboard.putData(Elevator.getInstance());
		SmartDashboard.putData(Intake.getInstance());
		SmartDashboard.putData(Arm.getInstance());

		SmartDashboard.putData(Scheduler.getInstance());
		
		SmartDashboard.putData(new TurnRightCommand());

		autoChooser = new SendableChooser();
		autoChooser.addDefault("Drive Only", new DriveCommand(.7, .7, 2));
		autoChooser.addObject("Pick Up 1 Tote", new AutoOneToteCommand());
		autoChooser.addObject("Pick Up 2 Totes", new AutoTwoToteCommand());

		SmartDashboard.putData("Autonomous", autoChooser);

		logFile = new LogFileCommand();
	}

	private void log() {
		SmartDashboard.putNumber("POT", Arm.getInstance().getPosition());
		logFile.putNumber("POT", Arm.getInstance().getPosition());
	}

	public void autonomousInit() {
		autoCommand = (Command) autoChooser.getSelected();
		autoCommand.start();
		if (!logFile.isRunning())
			logFile.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void teleopInit() {
		if (autoCommand != null)
			autoCommand.cancel();
		if (!logFile.isRunning())
			logFile.start();
		/*
		 * if (!logFile.isRunning()) logFile.start();
		 * 
		 * autoCommand.cancel(); if (!Elevator.getInstance().isEncoderReset())
		 * new ResetElevatorEncoderCommand().start();
		 */
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
