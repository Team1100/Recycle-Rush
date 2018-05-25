package org.team1100.subsystems;

import org.team1100.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Twitch extends Subsystem {

	private static Twitch miniArm;

	public static Twitch getInstance() {
		if (miniArm == null)
			miniArm = new Twitch();
		return miniArm;
	}

	private Victor victor;
	private DigitalInput limitSwitch;

	public Twitch() {
		victor = new Victor(RobotMap.MA_MOTOR);
		limitSwitch = new DigitalInput(6);
	}

	public boolean isOut() {
		return !limitSwitch.get();
	}

	/**
	 * Sets the speed of the motor
	 * 
	 * @param speed the speed, from -1 to 1
	 */
	public void move(double speed) {
		if (speed > 0 && isOut())
			speed = 0;
		victor.set(speed);
	}

	/**
	 * Spins the arm in the open direction if it isn't open already open
	 */
	public void open() {
		move(1);
	}

	/**
	 * Closes the arm in the closed direction if it isn't already closed
	 */
	public void close() {
		move(-1);
	}

	/**
	 * Stops the arm
	 */
	public void stop() {
		move(0);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
