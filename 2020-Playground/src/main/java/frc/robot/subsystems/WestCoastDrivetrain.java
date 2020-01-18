/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WestCoastDrivetrain extends SubsystemBase {
  
  WPI_TalonSRX leftMotorMaster;
  WPI_TalonSRX leftMotorSlave1;
  WPI_TalonSRX leftMotorSlave2;
  SpeedControllerGroup leftMotorGroup;

  WPI_TalonSRX rightMotorMaster;
  WPI_TalonSRX rightMotorSlave1;
  WPI_TalonSRX rightMotorSlave2;
  SpeedControllerGroup rightMotorGroup;

  // Initialize robot drive train
  DifferentialDrive westCoastDrive;

  public WestCoastDrivetrain() {

    //Initialize drivetrain
    initDrivetrain();

  }

  //This method initializes the entire drivetrain
  public void initDrivetrain() {

    //Initialize left side motors
    leftMotorMaster = new WPI_TalonSRX(MASTER_LEFT_MOTOR);
    leftMotorSlave1 = new WPI_TalonSRX(SLAVE1_LEFT_MOTOR);
    leftMotorSlave2 = new WPI_TalonSRX(SLAVE2_LEFT_MOTOR);
    leftMotorGroup = new SpeedControllerGroup(leftMotorMaster, leftMotorSlave1, leftMotorSlave2);
  
    //Initialize right side motors
    rightMotorMaster = new WPI_TalonSRX(MASTER_RIGHT_MOTOR);
    rightMotorSlave1 = new WPI_TalonSRX(SLAVE1_RIGHT_MOTOR);
    rightMotorSlave2 = new WPI_TalonSRX(SLAVE2_RIGHT_MOTOR);
    rightMotorGroup = new SpeedControllerGroup(rightMotorMaster, rightMotorSlave1, rightMotorSlave2);
  
    // Initialize robot drive train
    westCoastDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double leftJoyX, double leftJoyY, double rightJoyX, double rightJoyY) {

    if (DIRECTION_MULTIPLIER == 1)  //TODO: Change back to 1

      westCoastDrive.tankDrive(leftJoyY * -DIRECTION_MULTIPLIER, rightJoyY * -DIRECTION_MULTIPLIER);

    else

      westCoastDrive.tankDrive(leftJoyY * -DIRECTION_MULTIPLIER, rightJoyY * -DIRECTION_MULTIPLIER);
    
  }
 
}
