/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;
import static frc.robot.Constants.ShooterConstants.*;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  
  //Motor for Turret
  private WPI_TalonSRX turret = new WPI_TalonSRX(TURRET);
  private Encoder turretEncoder = new Encoder(0, 1);

  private boolean targetLock;

  public Turret() {

    //1-inch sprocket
    turretEncoder.setDistancePerPulse(1 * Math.PI / kTurretEncoderPPR);

  }

  
  @Override
  public void periodic() {

    targetLock = SmartDashboard.getBoolean("Target Lock", false);

    SmartDashboard.putBoolean("Target Lock", targetLock);
    SmartDashboard.putNumber("Encoder Distance", turretEncoder.getDistance());

  }

  public void runTurret(double speed){

    turret.set(speed);
  }

  public boolean getTargetLock(){

    return targetLock;
  }
  
}
