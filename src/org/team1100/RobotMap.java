package org.team1100;

import edu.wpi.first.wpilibj.DigitalSource;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//[D]rive
	public static final int D_FRONT_LEFT_CIM = 0;
	public static final int D_FRONT_RIGHT_CIM = 1;
	public static final int D_REAR_LEFT_CIM = 2;
	public static final int D_REAR_RIGHT_CIM = 3;

	//[C]ontrol
    public static final int C_LEFT_JOYSTICK = 2;
    public static final int C_RIGHT_JOYSTICK = 1;
    public static final int C_XBOX_CONTROLLER = 0;
    public static final int C_LAUNCHPAD_CONTROLLER = 3;
	public static final String CAMERA_NAME = "cam1";
    
    //[M]anipulator
    public static final int M_ELEVATOR_CIM_1 = 2;
    public static final int M_ELEVATOR_CIM_2 = 3;
	public static final int M_ENCODER_A = 0;
	public static final int M_ENCODER_B = 1;
	public static final int M_BEAM_BREAK = 2;
}
