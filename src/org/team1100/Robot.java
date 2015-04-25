package org.team1100;	

import org.team1100.commands.autonomous.OneToteAuto;
import org.team1100.commands.autonomous.ThreeToteAuto;
import org.team1100.commands.drive.Drive;
import org.team1100.commands.manipulator.twitch.OpenTwitch;
import org.team1100.commands.util.LogFileCommand;
import org.team1100.subsystems.Arm;
import org.team1100.subsystems.DriveTrain;
import org.team1100.subsystems.Elevator;
import org.team1100.subsystems.Intake;
import org.team1100.subsystems.Twitch;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
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
		Twitch.getInstance();

		autoChooser = new SendableChooser();
		autoChooser.addObject("Do Nothing", new WaitCommand(.5));
		autoChooser.addObject("Drive Only", new Drive(.7, .7, 2));
		autoChooser.addObject("Pick Up 1 Tote", new OneToteAuto());
		autoChooser.addDefault("Pick Up 3 Totes", new ThreeToteAuto());

		SmartDashboard.putData("Autonomous", autoChooser);
		SmartDashboard.putData(new OpenTwitch());
	}

	private void log() {
		Elevator.getInstance().log();
		Arm.getInstance().log();
		DriveTrain.getInstance().log();
		Intake.getInstance().log();
		SmartDashboard.putString("Auto Command", autoChooser.getSelected().getClass().getSimpleName());
	}

	public void autonomousInit() {
		autoCommand = (Command) autoChooser.getSelected();
		autoCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void teleopInit() {
		if (autoCommand != null)
			autoCommand.cancel();
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
