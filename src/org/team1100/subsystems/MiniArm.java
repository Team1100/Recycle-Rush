package org.team1100.subsystems;

import org.team1100.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class MiniArm extends Subsystem {

	private static MiniArm miniArm;

	public static MiniArm getInstance() {
		if (miniArm == null)
			miniArm = new MiniArm();
		return miniArm;
	}

	private Victor victor;
	private boolean isOpen;

	public MiniArm() {
		victor = new Victor(RobotMap.MA_MOTOR);
		isOpen = false;
		LiveWindow.addActuator("Mini Arm", "Arm", victor);
	}

	/**
	 * Sets whether the MiniArm is out. This is used to protect the
	 * {@link #open() open} and {@link #close() close} methods from damaging the
	 * robot
	 * 
	 * @param isOpen if the arm is open
	 */
	public void setOut(boolean isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * Returns whether the arm is open or not
	 * 
	 * @return whether the arm is open
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * Sets the speed of the motor
	 * 
	 * @param speed the speed, from -1 to 1
	 */
	public void spin(double speed) {
		victor.set(speed);
	}

	/**
	 * Spins the arm in the open direction if it isn't open already open
	 */
	public void open() {
		if (!isOpen)
			spin(1);

	}

	/**
	 * Closes the arm in the closed direction if it isn't already closed
	 */
	public void close() {
		if (isOpen())
			spin(-1);
	}

	/**
	 * Stops the arm
	 */
	public void stop() {
		spin(0);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
