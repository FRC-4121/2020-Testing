/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//This is a generic subsystem for testing purposes
public class GenericSubsystem extends SubsystemBase {
 
  //private final WPI_TalonSRX motor1 = new WPI_TalonSRX(GENERIC_TALON_1);
  private final WPI_TalonFX motor1 = new WPI_TalonFX(GENERIC_FALCON_1);
  //private CANSparkMax motor1 = new CANSparkMax(GENERIC_SPARK_1, MotorType.kBrushless);

  //private final WPI_TalonSRX motor2 = new WPI_TalonSRX(GENERIC_TALON_2);
  private final WPI_TalonFX motor2 = new WPI_TalonFX(GENERIC_FALCON_2);
  //private CANSparkMax motor2 = new CANSparkMax(GENERIC_SPARK_2, MotorType.kBrushless);
  
  
  public GenericSubsystem() {

    motor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 20);
    motor2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 20);
  }

  @Override
  public void periodic(){

    SmartDashboard.putNumber("Motor 1 Current", motor1.getSupplyCurrent());
    SmartDashboard.putNumber("Motor 2 Current", motor2.getSupplyCurrent());

    SmartDashboard.putNumber("Motor 1 Speed", motor1.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Motor 2 Speed", motor2.getSelectedSensorVelocity());
  }

  public void runMotor(double speed){

    motor1.set(speed);
    //motor2.set(speed);

  }

}
