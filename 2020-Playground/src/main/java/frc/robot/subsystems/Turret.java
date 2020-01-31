/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;
import static frc.robot.Constants.DrivetrainConstants.*;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  
  // WPI_TalonSRX leftMotorMaster;
  // WPI_TalonSRX leftMotorSlave1;
  // WPI_TalonSRX leftMotorSlave2;
  // SpeedControllerGroup leftMotorGroup;

  // WPI_TalonSRX rightMotorMaster;
  // WPI_TalonSRX rightMotorSlave1;
  // WPI_TalonSRX rightMotorSlave2;
  // SpeedControllerGroup rightMotorGroup;

  // // Initialize robot drive train
  // DifferentialDrive westCoastDrive;

  //Motor for Turret
  private WPI_TalonSRX turret = new WPI_TalonSRX(TURRET);

  private boolean targetLock;

  public Turret() {

    //Initialize drivetrain
    initDrivetrain();

  }

  //This method initializes the entire drivetrain
  public void initDrivetrain() {

    //Initialize left side motors
    // leftMotorMaster = new WPI_TalonSRX(MASTER_LEFT_MOTOR);
    // leftMotorSlave1 = new WPI_TalonSRX(SLAVE1_LEFT_MOTOR);
    // leftMotorSlave2 = new WPI_TalonSRX(SLAVE2_LEFT_MOTOR);
    // leftMotorGroup = new SpeedControllerGroup(leftMotorMaster, leftMotorSlave1, leftMotorSlave2);
  
    // //Initialize right side motors
    // rightMotorMaster = new WPI_TalonSRX(MASTER_RIGHT_MOTOR);
    // rightMotorSlave1 = new WPI_TalonSRX(SLAVE1_RIGHT_MOTOR);
    // rightMotorSlave2 = new WPI_TalonSRX(SLAVE2_RIGHT_MOTOR);
    // rightMotorGroup = new SpeedControllerGroup(rightMotorMaster, rightMotorSlave1, rightMotorSlave2);
  
    // //Initialize robot drive train
    // westCoastDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

    // //Set all talons to brake mode
    // leftMotorMaster.setNeutralMode(NeutralMode.Brake);
    // leftMotorSlave1.setNeutralMode(NeutralMode.Brake);
    // leftMotorSlave2.setNeutralMode(NeutralMode.Brake);
    // rightMotorMaster.setNeutralMode(NeutralMode.Brake);
    // rightMotorSlave1.setNeutralMode(NeutralMode.Brake);
    // rightMotorSlave2.setNeutralMode(NeutralMode.Brake);

    // //Configure encoders
    // //left motor
		// leftMotorMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		// leftMotorMaster.setSensorPhase(!kSensorPhase);
		// leftMotorMaster.setInverted(kMotorInvert);
		// int absolutePositionLeft = leftMotorMaster.getSensorCollection().getPulseWidthPosition();
		// /* mask out overflows, keep bottom 12 bits */
		// absolutePositionLeft &= 0xFFF;
		// if (!kSensorPhase)
		// 	absolutePositionLeft *= -1;
		// if (kMotorInvert) //need to mess with this
		// 	absolutePositionLeft *= -1;
		// leftMotorMaster.setSelectedSensorPosition(absolutePositionLeft, kPIDLoopIdx, kTimeoutMs);
		
		
		// //right motor
		// rightMotorMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		// rightMotorMaster.setSensorPhase(kSensorPhase); //might need to change kSensorPhaseinRobotMap
		// rightMotorMaster.setInverted(kMotorInvert);
		// int absolutePositionRight = rightMotorMaster.getSensorCollection().getPulseWidthPosition();

		// absolutePositionRight &= 0xFFF;
		// if (kSensorPhase)
		//   absolutePositionRight *= -1;
		// if (kMotorInvert) //need to mess with this
		//   absolutePositionRight *= -1;
    // rightMotorMaster.setSelectedSensorPosition(absolutePositionRight, kPIDLoopIdx, kTimeoutMs);

    //Initially set target locking system to true
    targetLock = false;
  
  }

  @Override
  public void periodic() {

    targetLock = SmartDashboard.getBoolean("Target Lock", false);

    SmartDashboard.putBoolean("Target Lock", targetLock);

  }

  // public void drive(double leftJoyX, double leftJoyY, double rightJoyX, double rightJoyY) {

  //   if (DIRECTION_MULTIPLIER == 1)

  //     westCoastDrive.tankDrive(leftJoyY * -DIRECTION_MULTIPLIER, rightJoyY * -DIRECTION_MULTIPLIER);

  //   else

  //     westCoastDrive.tankDrive(leftJoyY * -DIRECTION_MULTIPLIER, rightJoyY * -DIRECTION_MULTIPLIER);
    
  // }

  // public void autoDrive(double leftSpeed, double rightSpeed){


  //   westCoastDrive.tankDrive(leftSpeed, rightSpeed);

  // }

  // public void autoStop(){

  //   westCoastDrive.tankDrive(0, 0);
  // }

  public void runTurret(double speed){

    turret.set(speed);
  }

  public boolean getTargetLock(){

    return targetLock;
  }
  
}
