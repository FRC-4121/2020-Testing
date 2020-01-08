/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This class defines the standard, 6-wheel West Coast Drive train
 */
public class WestCoastDriveTrain extends GenericDriveTrain {

  // Initializing all WPI_TalonSRXs using CAN
  WPI_TalonSRX leftMotorMaster = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_MASTER);
  WPI_TalonSRX leftMotorSlave1 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_SLAVE_1);
  WPI_TalonSRX leftMotorSlave2 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_SLAVE_2);
  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotorMaster, leftMotorSlave1, leftMotorSlave2);

  WPI_TalonSRX rightMotorMaster = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_MASTER);
  WPI_TalonSRX rightMotorSlave1 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_SLAVE_1);
  WPI_TalonSRX rightMotorSlave2 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_SLAVE_2);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotorMaster, rightMotorSlave1, rightMotorSlave2);


  // Initialize robot drive train
  DifferentialDrive westCoastDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);


  // Teleop drive method
  @Override
  public void drive(double leftJoyX, double leftJoyY, double rightJoyX, double rightJoyY) {

    if (RobotMap.DIRECTION_MULTIPLIER == 1)

      westCoastDrive.tankDrive(leftJoyY * -RobotMap.DIRECTION_MULTIPLIER, rightJoyY * -RobotMap.DIRECTION_MULTIPLIER);

    else

      westCoastDrive.tankDrive(leftJoyY * -RobotMap.DIRECTION_MULTIPLIER, rightJoyY * -RobotMap.DIRECTION_MULTIPLIER);
    
    westCoastDrive.setSafetyEnabled(false);

    westCoastDrive.setMaxOutput(0.8);
    
  }

  // WCD autonomous drive method
  @Override
  public void autoDrive(double leftSpeed, double rightSpeed) {

    westCoastDrive.tankDrive(leftSpeed, rightSpeed);
    
  }

  // Mecanum autonomous drive method - not used
  @Override
  public void autoDrive(double speed, double angle, double rotation) {

  }

}
