package org.team1100.subsystems;

import org.team1100.Robot;
import org.team1100.RobotMap;
import org.team1100.commands.drive.UserDriveCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	private RobotDrive drive;

	/**
	 * Initializes the DriveSubsystem, using the talon values from RobotMap
	 */
	public DriveSubsystem() {
		Victor leftCIM = new Victor(RobotMap.D_FRONT_LEFT_CIM);
		Victor rightCIM = new Victor(RobotMap.D_FRONT_RIGHT_CIM);
		
		drive = new RobotDrive(leftCIM, rightCIM);
	}

	/**
	 * This is repeatedly called in the UserDriveCommand, allowing the operator
	 * to drive the robot
	 */
	public void userDriveTank() {
		double leftValue = Robot.OI.getLeftJoystick().getAxis(Joystick.AxisType.kY);
		double rightValue = Robot.OI.getRightJoystick().getAxis(Joystick.AxisType.kY);

		drive.tankDrive(leftValue, rightValue);
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

	/**
	 * Stops the drive train
	 */
	public void stop() {
		drive.tankDrive(0, 0);
	}

	/**
	 * Sets the default command to drive the robot via an operator. This command
	 * is used when no other commands are using the subsystem
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new UserDriveCommand());
	}

}
