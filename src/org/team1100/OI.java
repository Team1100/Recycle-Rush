package org.team1100;

import org.team1100.commands.manipulator.PickUpTote;
import org.team1100.commands.manipulator.ScoreTotes;
import org.team1100.commands.manipulator.arm.ToggleArmClaw;
import org.team1100.commands.manipulator.arm.ToggleRotateArmClaw;
import org.team1100.commands.manipulator.elevator.ResetElevatorEncoder;
import org.team1100.commands.manipulator.intake.ToggleIntakeClaw;
import org.team1100.input.AttackThree;
import org.team1100.input.LaunchpadController;
import org.team1100.input.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static OI oi;

	public static OI getInstance() {
		if (oi == null)
			oi = new OI();
		return oi;
	}

	private AttackThree rightStick;
	private AttackThree leftStick;
	private XboxController xbox;
	private LaunchpadController launchPad;

	private OI() {
		rightStick = new AttackThree(RobotMap.C_RIGHT_JOYSTICK, 0.1);
		leftStick = new AttackThree(RobotMap.C_LEFT_JOYSTICK, 0.1);
		xbox = new XboxController(RobotMap.C_XBOX_CONTROLLER, 0.2);
		launchPad = new LaunchpadController(RobotMap.C_LAUNCHPAD_CONTROLLER);

		xbox.getButtonLeftBumper().whenPressed(new ToggleArmClaw());
		xbox.getButtonRightBumper().whenPressed(new ToggleIntakeClaw());
		xbox.getButtonA().whenPressed(new ToggleRotateArmClaw());
		// xbox.getButtonX().toggleWhenPressed(new ScoreTotes());
		xbox.getButtonB().toggleWhenPressed(new PickUpTote());
		xbox.getButtonBack().whenPressed(new ResetElevatorEncoder());
	}

	/**
	 * Returns the instance of the right {@link AttackThree AttackThree
	 * Joystick} to be able to get the value of the axis' and to test whether a
	 * button is pressed
	 * 
	 * @return the instance of the right {@link AttackThree AttackThree
	 *         Joystick}
	 */
	public AttackThree getRightJoystick() {
		return rightStick;
	}

	/**
	 * Returns the instance of the left {@link AttackThree AttackThree Joystick}
	 * to be able to get the value of the axis' and to test whether a button is
	 * pressed
	 * 
	 * @return the instance of the left {@link AttackThree AttackThree Joystick}
	 */
	public AttackThree getLeftJoystick() {
		return leftStick;
	}

	/**
	 * Returns the instance of the {@link XboxController} to be able to get the
	 * value of the axis' and to test whether a button is pressed
	 * 
	 * @return the instance of the {@link XboxController}
	 */
	public XboxController getXboxController() {
		return xbox;
	}

	public LaunchpadController getLaunchpad() {
		return launchPad;

	}
}
