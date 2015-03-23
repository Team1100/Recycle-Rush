package org.team1100;

import org.team1100.commands.autonomous.OneToteAuto;
import org.team1100.commands.autonomous.ThreeToteAuto;
import org.team1100.commands.autonomous.TwoToteAuto;
import org.team1100.commands.drive.Drive;
import org.team1100.commands.drive.Turn;
import org.team1100.commands.util.LogFileCommand;
import org.team1100.subsystems.Arm;
import org.team1100.subsystems.DriveTrain;
import org.team1100.subsystems.Elevator;
import org.team1100.subsystems.Intake;
import org.team1100.subsystems.MiniArm;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
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

	private SendableChooser autoChooser;

	public void robotInit() {
		OI.getInstance();

		logFile = new LogFileCommand();
		try {
			CameraServer.getInstance().setQuality(50);
			CameraServer.getInstance().startAutomaticCapture(RobotMap.CAMERA_NAME);
		} catch (Exception e) {
			DriverStation.reportError(e.getMessage(), true);
		}
		DriveTrain.getInstance();
		Elevator.getInstance();
		Intake.getInstance();
		Arm.getInstance();
		MiniArm.getInstance();

		/*
		 * SmartDashboard.putData(DriveTrain.getInstance());
		 * SmartDashboard.putData(Elevator.getInstance());
		 * SmartDashboard.putData(Intake.getInstance());
		 * SmartDashboard.putData(Arm.getInstance());
		 * SmartDashboard.putData(MiniArm.getInstance());
		 */

		SmartDashboard.putData(Scheduler.getInstance());

		autoChooser = new SendableChooser();
		autoChooser.addObject("Drive Only", new Drive(.7, .7, 2));
		autoChooser.addObject("Pick Up 1 Tote", new OneToteAuto());
		autoChooser.addObject("Pick Up 2 Totes", new TwoToteAuto());
		autoChooser.addDefault("Pick Up 3 Totes", new ThreeToteAuto());

		// SmartDashboard.putData(new TurnLeft());
		// SmartDashboard.putData("Turn 90 Degrees Right", new Turn(90));
		SmartDashboard.putData("Autonomous", autoChooser);
		SmartDashboard.putData(new Turn(90));
	}

	private void log() {
		Elevator.getInstance().log();
		Arm.getInstance().log();
		DriveTrain.getInstance().log();
		Intake.getInstance().log();
		// logFile.putNumber("POT", Arm.getInstance().getPosition());
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
