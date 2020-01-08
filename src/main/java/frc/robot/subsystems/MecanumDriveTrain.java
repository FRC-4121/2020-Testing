/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * This class defines the standard, 4-wheel mecanum drive train
 */
public class MecanumDriveTrain extends GenericDriveTrain {

  //Define local variables
  double gyroAngle;
  double speedX, speedY, speedZ;

	//Initialize motor controllers
  WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR_ID);
  WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR_ID);    
  WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(RobotMap.BACK_LEFT_MOTOR_ID);
  WPI_TalonSRX backRightMotor = new WPI_TalonSRX(RobotMap.BACK_RIGHT_MOTOR_ID);

  //Initialize mecanum drive
  MecanumDrive mecanumDrive = new MecanumDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);

  //Teleop drive method
  @Override
  public void drive(double rightJoyX, double rightJoyY, double rightJoyZ, boolean useGyro) {
    
    //Set properties of drive
    mecanumDrive.setSafetyEnabled(false);	
    mecanumDrive.setMaxOutput(0.8);
    
    //Get joystick values and scale
    speedX = rightJoyX * RobotMap.DRIVE_SPEED;
    speedY = rightJoyY * RobotMap.DRIVE_SPEED;
    speedZ = rightJoyZ * RobotMap.DRIVE_SPEED;

    //If using the pi gyro board, mod the returned value by 360 to avoid a spinning robot
    if(useGyro) {

      gyroAngle = Robot.driveAngle.getDouble(0) % 360.0;
  
      mecanumDrive.driveCartesian(speedX,  speedY,  speedZ, gyroAngle);
  
    } else {

      mecanumDrive.driveCartesian(speedX,  speedY,  speedZ);

    }

    SmartDashboard.putString("Gyro Angle", Double.toString(gyroAngle));
    SmartDashboard.putString("Gyro Yaw", Double.toString(Robot.gyroYaw.getDouble(0)));
  }


  // WCD autonomous drive method - not used.  Needs to be here for inheritance reasons
  @Override
  public void autoDrive(double leftSpeed, double rightSpeed) {
  }

  //Mecanum autonomous drive method
  @Override
  public void autoDrive(double speed, double angle, double rotation){

    mecanumDrive.setSafetyEnabled(false);
		
		mecanumDrive.setMaxOutput(0.8);

    mecanumDrive.drivePolar(speed, angle, rotation);

  }

}
