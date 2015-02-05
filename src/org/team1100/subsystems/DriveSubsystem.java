package org.team1100.subsystems;

import org.team1100.Robot;
import org.team1100.RobotMap;
import org.team1100.commands.drive.UserDriveCommand;
import org.team1100.input.XboxController;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	private RobotDrive drive;

	/**
	 * Initializes the DriveSubsystem, using the talon values from RobotMap
	 */
	public DriveSubsystem() {
		drive = new RobotDrive(RobotMap.D_FRONT_LEFT_CIM, RobotMap.D_REAR_LEFT_CIM,
				RobotMap.D_FRONT_RIGHT_CIM, RobotMap.D_REAR_RIGHT_CIM);
	}

	/**
	 * This is repeatedly called in the UserDriveCommand, allowing the operator
	 * to drive the robot
	 */
	public void userDriveTank() {
		double leftValue = Robot.OI.getLeftJoystick().getAxis(Joystick.AxisType.kY);
		double rightValue = Robot.OI.getRightJoystick().getAxis(Joystick.AxisType.kY);
		SmartDashboard.putNumber("Left Value", leftValue);
		SmartDashboard.putNumber("Right Value", rightValue);

		drive.tankDrive(leftValue, rightValue);
	}

	/**
	 * Drives the robot in tank given the speed
	 * 
	 * @param leftValue the speed of the left side, from -1 to 1
	 * @param rightValue the speed of the right side, from -1 to 1
	 */
	public void driveTank(double leftValue, double rightValue) {
		drive.tankDrive(-leftValue, -rightValue);
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
