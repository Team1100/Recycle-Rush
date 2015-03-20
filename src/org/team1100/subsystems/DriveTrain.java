package org.team1100.subsystems;

import org.team1100.RobotMap;
import org.team1100.commands.drive.UserDrive;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	private static DriveTrain driveTrain;

	public static DriveTrain getInstance() {
		if (driveTrain == null)
			driveTrain = new DriveTrain();
		return driveTrain;
	}

	private RobotDrive drive;
	private Victor victorLeft;
	private Victor victorRight;
	private Gyro gyro;

	/**
	 * Initializes the DriveTrain, using the talon values from RobotMap
	 */
	private DriveTrain() {
		victorLeft = new Victor(RobotMap.D_LEFT_MOTOR);
		victorRight = new Victor(RobotMap.D_RIGHT_MOTOR);

		drive = new RobotDrive(victorLeft, victorRight);

		gyro = new Gyro(RobotMap.D_GYRO);
		gyro.reset();
		
		LiveWindow.addActuator("Drive Train", "Left Victor", victorLeft);
		LiveWindow.addActuator("Drive Train", "Right Victor", victorRight);
		LiveWindow.addSensor("Drive Train", "Gyro", gyro);
	}

	/**
	 * Drives the robot in tank given the speed
	 * 
	 * @param leftValue the speed of the left side, from -1 to 1
	 * @param rightValue the speed of the right side, from -1 to 1
	 */
	public void driveTank(double leftValue, double rightValue) {
		drive.tankDrive(leftValue, rightValue);
	}
	
	public double getHeading(){
		return gyro.getAngle();
	}

	/**
	 * Sets the default command to drive the robot via an operator. This command
	 * is used when no other commands are using the subsystem
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new UserDrive());
	}
	
	public void log(){
		SmartDashboard.putNumber("Gyro", getHeading());
	}

}
