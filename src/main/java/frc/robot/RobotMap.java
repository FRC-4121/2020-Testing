/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//Motor Controller Constants - Mecanum Drive
	public static final int BACK_LEFT_MOTOR_ID = -1;
	public static final int BACK_RIGHT_MOTOR_ID = -1;
	public static final int FRONT_LEFT_MOTOR_ID = -1;
	public static final int FRONT_RIGHT_MOTOR_ID = -1;

	//Motor Controller Constants - WCD
	//(Not for 2019 Season Use)
	public static final int LEFT_MOTOR_MASTER = 3;
	public static final int LEFT_MOTOR_SLAVE_1 = 4;
	public static final int LEFT_MOTOR_SLAVE_2 = -1;
	public static final int RIGHT_MOTOR_MASTER = 0;
	public static final int RIGHT_MOTOR_SLAVE_1 = 1;
	public static final int RIGHT_MOTOR_SLAVE_2 = -1;
	public static final int BELT_MOTOR = 3;

	//Invert direction (for WCD only)
	public static int DIRECTION_MULTIPLIER = 1;
	
	//Speed multiplier for more accurate driving in mecanum
	public static final double MECANUM_MULTIPLIER = 0.5;

	//Motor speeds
	public static final double AUTO_DRIVE_SPEED = 0.8;
	public static double DRIVE_SPEED = 0.65;
	public static final double DRIVE_SPEED_FAST = 1.0;
	public static final double DRIVE_SPEED_SLOW = 0.25;
	public static final double STOP_SPEED = 0;
	public static final double BELT_UP_SPEED = 1.0;
	public static final double BELT_DOWN_SPEED = -1.0;

	//PID values for mecanum
	public static final double kP_Straight = 0.05;
	public static final double kP_Turn = 0.05;
	public static final double kI_Straight = 0.0;
	public static final double kI_Turn = 0.0;
	public static final double kD_Straight = 0.004;
	public static final double kD_Turn = 0.004;
}