/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;
import static frc.robot.Constants.ShooterConstants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.extraClasses.UltrasonicSensor;

public class ShooterSameShaft extends SubsystemBase {
  
  private final WPI_TalonFX shooter1 = new WPI_TalonFX(SHOOTER_MASTER);
  private final WPI_TalonFX shooter2 = new WPI_TalonFX(SHOOTER_SLAVE);

  private final WPI_TalonSRX turret = new WPI_TalonSRX(TURRET);

  private final Encoder turretEncoder = new Encoder(TURRET_ENCODER_1, TURRET_ENCODER_2);
  private final DigitalInput turretLimit = new DigitalInput(TURRET_LIMIT_SWITCH);

  private final PIDController turretPID = new PIDController(kP_Turret, kI_Turret, kD_Turret);

  private boolean resetEncoder = false;
 

  public ShooterSameShaft() {
    
    shooter2.setInverted(InvertType.OpposeMaster);

    shooter1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);

    //theta (radians) = arclength / radius
    turretEncoder.setDistancePerPulse(-kTurretSprocketDia * 360 / (kTurretEncoderPPR * kTurretGearReduction * kTurretDiskDia/2));

    turretPID.setTolerance(5, 10);

  }

  @Override
  public void periodic() {

    SmartDashboard.putNumber("Encoder Distance", turretEncoder.getDistance());
    SmartDashboard.putBoolean("Turret Limit", turretLimit.get());

    if (turretLimit.get() == false)
    {
      //...and the reset flag is false...
      if(!resetEncoder)
      {
        //...reset the encoder and lock
        turretEncoder.reset();
        turret.set(ControlMode.Position, turretPID.calculate(turretEncoder.getDistance(), 0));
        resetEncoder = true;
      }
    }
    else //if the limit switch moves away, make it reset the next time it comes down.
    {
      resetEncoder = false;
    }
  }

  public void runShooter(double speed){

    // shooter1PID.setP(5e-5);
    // shooter1PID.setI(1e-6);
    // shooter1PID.setD(0);
    // shooter1PID.setOutputRange(-1, 1);
    
    double setPoint = speed*6000;

    //shooter1PID.setReference(setPoint, ControlType.kVelocity);

    shooter1.set(speed);
    shooter2.set(-speed); //inverted because we are attempting to put two motors on one shaft.

    SmartDashboard.putNumber("Motor 1 Current", shooter1.getSupplyCurrent());
    SmartDashboard.putNumber("Motor 2 Current", shooter2.getSupplyCurrent());
    SmartDashboard.putNumber("Motor 1 RPM", shooter1.getSelectedSensorVelocity() * 10 * 60 / kTalonFXPPR);//method returns raw units per 100ms (why, I don't know)
    SmartDashboard.putNumber("Motor 2 RPM", shooter2.getSelectedSensorVelocity() * 10 * 60 / kTalonFXPPR);

  }

  public void rotateShooter(double speed){

    turret.set(speed);
  }
 
}
