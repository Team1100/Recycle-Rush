package org.team1100;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Physical device input/outputs

	// RoboRIO PWM outputs
	public static final int PWM_0 = 0;
	public static final int PWM_1 = 1;
	public static final int PWM_2 = 2;
	public static final int PWM_3 = 3;
	public static final int PWM_4 = 4;
	public static final int PWM_5 = 5;
	public static final int PWM_6 = 6;
	public static final int PWM_7 = 7;
	public static final int PWM_8 = 8;
	public static final int PWM_9 = 9;

	// RoboRIO digital inputs or outputs
	public static final int DIO_0 = 0;
	public static final int DIO_1 = 1;
	public static final int DIO_2 = 2;
	public static final int DIO_3 = 3;
	public static final int DIO_4 = 4;
	public static final int DIO_5 = 5;
	public static final int DIO_6 = 6;
	public static final int DIO_7 = 7;
	public static final int DIO_8 = 8;
	public static final int DIO_9 = 9;

	// RoboRIO relay outputs
	public static final int RELAY_0 = 0;
	public static final int RELAY_1 = 1;
	public static final int RELAY_2 = 2;
	public static final int RELAY_3 = 3;

	// RoboRIO analog inputs
	public static final int ANALOG_IN_0 = 0;
	public static final int ANALOG_IN_1 = 1;
	public static final int ANALOG_IN_2 = 2;
	public static final int ANALOG_IN_3 = 3;

	// PCM outputs
	public static final int PNEUMATIC_0 = 0;
	public static final int PNEUMATIC_1 = 1;
	public static final int PNEUMATIC_2 = 2;
	public static final int PNEUMATIC_3 = 3;
	public static final int PNEUMATIC_4 = 4;
	public static final int PNEUMATIC_5 = 5;
	public static final int PNEUMATIC_6 = 6;
	public static final int PNEUMATIC_7 = 7;

	// add CAN_ID constants as necessary
	public static final int CAN_ID_1 = 1;
	public static final int CAN_ID_2 = 2;
	public static final int CAN_ID_3 = 3;
	public static final int CAN_ID_4 = 4;
	public static final int CAN_ID_5 = 5;
	public static final int CAN_ID_6 = 6;
	public static final int CAN_ID_7 = 7;
	public static final int CAN_ID_8 = 8;

	// Driver Station USB ids
	public static final int DS_USB_0 = 0;
	public static final int DS_USB_1 = 1;
	public static final int DS_USB_2 = 2;
	public static final int DS_USB_3 = 3;
	public static final int DS_USB_4 = 4;
	public static final int DS_USB_5 = 5;

	// [D]rive Train
	public static final int D_LEFT_MOTOR = PWM_0;
	public static final int D_RIGHT_MOTOR = PWM_1;
	public static final int D_GYRO = ANALOG_IN_1;

	// [C]ontrol
	public static final int C_XBOX_CONTROLLER = DS_USB_0;
	public static final int C_LEFT_JOYSTICK = DS_USB_1;
	public static final int C_RIGHT_JOYSTICK = DS_USB_2;
	public static final int C_LAUNCHPAD_CONTROLLER = DS_USB_3;
	public static final int PCM_ID = CAN_ID_1;
	public static final String CAMERA_NAME = "cam1";
	public static final String USB_FILE_LOCATION = "/media/sda1/logs";

	// [E]levator
	public static final int E_ELEVATOR_CIM_1 = CAN_ID_2;
	public static final int E_ELEVATOR_CIM_2 = CAN_ID_3;
	public static final int E_ENCODER_A = DIO_0;
	public static final int E_ENCODER_B = DIO_1;
	public static final int E_LIMIT_SWITCH = DIO_2;
	public static final int E_BANNER_SENSOR_BACK = DIO_3;
	public static final int E_BANNER_SENSOR_FRONT = DIO_4;

	// [I]ntake
	public static final int I_LEFT_MOTOR = PWM_4;
	public static final int I_RIGHT_MOTOR = PWM_5;
	public static final int I_SOLENOID_A = PNEUMATIC_0;
	public static final int I_SOLENOID_B = PNEUMATIC_1;

	// [A]rm
	public static final int A_LEFT_MOTOR = PWM_2; 
	public static final int A_RIGHT_MOTOR = PWM_3; 
	public static final int A_POTENTIOMETER = ANALOG_IN_0;
	public static final int A_GRIPPER_SOLENOID_A = PNEUMATIC_4;
	public static final int A_GRIPPER_SOLENOID_B = PNEUMATIC_5;
	public static final int A_CLAW_ROTATE_SOLENOID_B = PNEUMATIC_2;
	public static final int A_CLAW_ROTATE_SOLENOID_A = PNEUMATIC_3;
	public static final int A_BANNER_SENSOR = DIO_5;
	
	// [M]ini[A]rm
	public static final int MA_MOTOR = PWM_6;
}
