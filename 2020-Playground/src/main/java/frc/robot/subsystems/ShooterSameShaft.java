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
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSameShaft extends SubsystemBase {
  
  //private final CANSparkMax shooter1 = new CANSparkMax(SHOOTER_MASTER, MotorType.kBrushless);
  //private final CANSparkMax shooter2 = new CANSparkMax(SHOOTER_SLAVE, MotorType.kBrushless);
  private final WPI_TalonFX shooter1 = new WPI_TalonFX(SHOOTER_MASTER);
  private final WPI_TalonFX shooter2 = new WPI_TalonFX(SHOOTER_SLAVE);

  private final WPI_TalonSRX turret = new WPI_TalonSRX(TURRET);
  private final Encoder turretEncoder = new Encoder(TURRET_ENCODER_1, TURRET_ENCODER_2);
 
  //private final CANPIDController shooter1PID = shooter1.getPIDController();
  
  //private final CANEncoder shooter1Encoder = shooter1.getEncoder();
  //private final CANEncoder shooter2Encoder = shooter2.getEncoder();


  public ShooterSameShaft() {

    shooter2.setInverted(InvertType.OpposeMaster);

    shooter1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
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
