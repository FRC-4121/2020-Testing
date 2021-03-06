/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;
import static frc.robot.Constants.DrivetrainConstants.*;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.IdleMode;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WestCoastDrivetrain extends SubsystemBase {

  private final ADXRS450_Gyro rioGyro = new ADXRS450_Gyro();
  
  //WPI_TalonSRX leftMotorMaster;
  //WPI_TalonSRX leftMotorSlave1;
  //WPI_TalonSRX leftMotorSlave2;
  //CANSparkMax leftMotorMaster;
  //CANSparkMax leftMotorSlave1;
  //CANSparkMax leftMotorSlave2;
  private WPI_TalonFX leftMotorMaster;
  private WPI_TalonFX leftMotorSlave1;
  SpeedControllerGroup leftMotorGroup;

  //WPI_TalonSRX rightMotorMaster;
  //WPI_TalonSRX rightMotorSlave1;
  //WPI_TalonSRX rightMotorSlave2;
  //CANSparkMax rightMotorMaster;
  //CANSparkMax rightMotorSlave1;
  //CANSparkMax rightMotorSlave2;
  private WPI_TalonFX rightMotorMaster;
  private WPI_TalonFX rightMotorSlave1;
  SpeedControllerGroup rightMotorGroup;

  // Initialize robot drive train
  DifferentialDrive westCoastDrive;

  public WestCoastDrivetrain() {

    //Initialize drivetrain
    initDrivetrain();

  }

  //This method initializes the entire drivetrain
  private void initDrivetrain() {

    if(USE_3_MOTORS){
      //Initialize left side motors
      //leftMotorMaster = new CANSparkMax(MASTER_LEFT_MOTOR, MotorType.kBrushless); //new WPI_TalonSRX(MASTER_LEFT_MOTOR);
      //leftMotorSlave1 = new CANSparkMax(SLAVE1_LEFT_MOTOR, MotorType.kBrushless); //new WPI_TalonSRX(SLAVE1_LEFT_MOTOR);
      //leftMotorSlave2 = new CANSparkMax(SLAVE2_LEFT_MOTOR, MotorType.kBrushless); //new WPI_TalonSRX(SLAVE2_LEFT_MOTOR);
      leftMotorMaster = new WPI_TalonFX(MASTER_LEFT_MOTOR);
      leftMotorSlave1 = new WPI_TalonFX(SLAVE1_LEFT_MOTOR);
      leftMotorGroup = new SpeedControllerGroup(leftMotorMaster, leftMotorSlave1);
    
      //Initialize right side motors
      //rightMotorMaster = new CANSparkMax(MASTER_RIGHT_MOTOR, MotorType.kBrushless); //WPI_TalonSRX(MASTER_RIGHT_MOTOR);
      //rightMotorSlave1 = new CANSparkMax(SLAVE1_RIGHT_MOTOR, MotorType.kBrushless); //WPI_TalonSRX(SLAVE1_RIGHT_MOTOR);
      //rightMotorSlave2 = new CANSparkMax(SLAVE2_RIGHT_MOTOR, MotorType.kBrushless); //WPI_TalonSRX(SLAVE2_RIGHT_MOTOR);
      rightMotorMaster = new WPI_TalonFX(MASTER_RIGHT_MOTOR);
      rightMotorSlave1 = new WPI_TalonFX(SLAVE1_RIGHT_MOTOR);
      rightMotorGroup = new SpeedControllerGroup(rightMotorMaster, rightMotorSlave1);
    
      //Initialize robot drive train
      westCoastDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

      //Set all talons to brake mode
      //leftMotorMaster.setIdleMode(IdleMode.kBrake);
      //leftMotorSlave1.setIdleMode(IdleMode.kBrake);
      //leftMotorSlave2.setIdleMode(IdleMode.kBrake);
      //rightMotorMaster.setIdleMode(IdleMode.kBrake);
      //rightMotorSlave1.setIdleMode(IdleMode.kBrake);
      //rightMotorSlave2.setIdleMode(IdleMode.kBrake);

    } else {

      // //Initialize left side motors
      // leftMotorMaster = new WPI_TalonSRX(TMD_MASTER_LEFT_MOTOR);
      // leftMotorSlave1 = new WPI_TalonSRX(TMD_SLAVE_LEFT_MOTOR);
      // leftMotorGroup = new SpeedControllerGroup(leftMotorMaster, leftMotorSlave1);
    
      // //Initialize right side motors
      // rightMotorMaster = new WPI_TalonSRX(TMD_MASTER_RIGHT_MOTOR);
      // rightMotorSlave1 = new WPI_TalonSRX(TMD_SLAVE_RIGHT_MOTOR);
      // rightMotorGroup = new SpeedControllerGroup(rightMotorMaster, rightMotorSlave1);
    
      // //Initialize robot drive train
      // westCoastDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

      // //Set all talons to brake mode
      // leftMotorMaster.setNeutralMode(NeutralMode.Brake);
      // leftMotorSlave1.setNeutralMode(NeutralMode.Brake);
      // rightMotorMaster.setNeutralMode(NeutralMode.Brake);
      // rightMotorSlave1.setNeutralMode(NeutralMode.Brake);


    }

    //Configure encoders
    //left motor
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
    
    // zeroEncoders();
    zeroGyro();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler 
    SmartDashboard.putNumber("Rio Gyro Angle", getGyroAngle());
    // SmartDashboard.putNumber("Left Encoder", getLeftEncoder());
    // SmartDashboard.putNumber("Right Encoder", getRightEncoder());

    if(SmartDashboard.getNumber("Zero Gyro", 0) == 1) {

      zeroGyro();
    }

    if(SmartDashboard.getNumber("Zero Encoders", 0) == 1){

      zeroEncoders();
    }
      

  }

  public void drive(double leftJoyX, double leftJoyY, double rightJoyX, double rightJoyY) {

    if (DIRECTION_MULTIPLIER == 1)

      westCoastDrive.tankDrive(leftJoyY * -DIRECTION_MULTIPLIER, rightJoyY * -DIRECTION_MULTIPLIER);

    else

      westCoastDrive.tankDrive(leftJoyY * -DIRECTION_MULTIPLIER, rightJoyY * -DIRECTION_MULTIPLIER);
    
  }

  public void autoDrive(double leftSpeed, double rightSpeed){


    westCoastDrive.tankDrive(leftSpeed, rightSpeed);

  }

  public void autoStop(){

    westCoastDrive.tankDrive(0, 0);
  }

  public int getLeftEncoder(){

    return 0;//leftMotorMaster.getSelectedSensorPosition();
  }

  public int getRightEncoder(){

    return 0;//rightMotorMaster.getSelectedSensorPosition();
  }

  public double getGyroAngle(){

    return rioGyro.getAngle();
  }

  public void zeroEncoders(){

    //leftMotorMaster.setSelectedSensorPosition(0);
    //rightMotorMaster.setSelectedSensorPosition(0);
  }

  public void zeroGyro(){

    rioGyro.reset();
  }

  public void invertDirection(){

    DIRECTION_MULTIPLIER *= -1;
  }
 
}
