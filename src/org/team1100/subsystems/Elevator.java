package org.team1100.subsystems;

import org.team1100.Robot;
import org.team1100.RobotMap;
import org.team1100.commands.manipulator.ElevatorCommand;
import org.team1100.input.XboxController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends PIDSubsystem {
    
	private double SPEED_PERCENT = .5;
	private double RESET_SPEED = 1;
	private double TOP_SETPOINT = 1; //TODO Find the top of the elevator
	private double BOTTOM_SETPOINT = 0; 
	
	private CANTalon talon1;
	private CANTalon talon2;
	private Encoder encoder;
	private DigitalInput beamBreak;
	
	
	public Elevator(){
		super(1,0,0);		
		talon1 = new CANTalon(RobotMap.M_ELEVATOR_CIM_1);
		talon2 = new CANTalon(RobotMap.M_ELEVATOR_CIM_2);
		encoder = new Encoder(RobotMap.M_ENCODER_A, RobotMap.M_ENCODER_B);
		resetEncoder();
		
		beamBreak = new DigitalInput(RobotMap.M_ENCODER_A);
	}
	
	public void userLift(){
		double speed = 0;
		double leftSpeed = Robot.OI.getXboxController().getAxis(XboxController.XboxAxis.kLeftTrigger);
		double rightSpeed = Robot.OI.getXboxController().getAxis(XboxController.XboxAxis.kRightTrigger);
		
		if (leftSpeed != 0)
			speed = leftSpeed * SPEED_PERCENT;
		else if (rightSpeed != 0)
			speed = -rightSpeed  * SPEED_PERCENT;
		
		lift(speed);
	}	
	
	public void lift(double speed){
		if (beamBreak.get())
			speed = 0;
		talon1.set(speed);
		talon2.set(speed);
		//SmartDashboard.putNumber("Talon 1 Current", talon1.getOutputCurrent());
		//SmartDashboard.putNumber("Talon 2 Current", talon2.getOutputCurrent());
		
		//SmartDashboard.putNumber("Lift Current", talon1.getOutputCurrent() + talon2.getOutputCurrent());
	}
	
	public void stop(){
		lift(0);
	}
	
	public double getPosition(){
		return encoder.get();
	}
	
	public void resetEncoder(){
		while (!beamBreak.get())
			lift(-RESET_SPEED);
		encoder.reset();
	}
	
	public double getSpeed(){
		return encoder.getRate();
	}

	@Override
	protected double returnPIDInput() {
		return encoder.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		lift(output);
	}

	public void goToBottom(){
		setSetpoint(BOTTOM_SETPOINT);
		enable();
	}
	
	public void goToTop() {
		setSetpoint(TOP_SETPOINT);
		enable();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorCommand());
	}
}

