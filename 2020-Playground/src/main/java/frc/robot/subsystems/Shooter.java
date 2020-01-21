/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.ShooterConstants.*;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  
  private final WPI_TalonSRX masterShooter = new WPI_TalonSRX(SHOOTER_MASTER);
  //private final WPI_TalonSRX slaveShooter = new WPI_TalonSRX(SHOOTER_SLAVE);

  public Shooter() {

    //initMotors();

  }

  private void initMotors(){

    //masterShooter.configSelectedFeedbackSensor(FeedbackDevice.CTMagEncoder_Relative);
    //masterShooter.setSensorPhase(true);

  }

  public void runShooter(double speed){

    //masterShooter.set(speed);
    masterShooter.set(speed);
    //slaveShooter.set(-speed); //inverted because we are attempting to put two motors on one shaft.
    SmartDashboard.putNumber("Motor Current", masterShooter.getSupplyCurrent());
    SmartDashboard.putNumber("Motor Speed", masterShooter.get());
  }
}
