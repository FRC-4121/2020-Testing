/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;
import static frc.robot.Constants.ShooterConstants.*;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSameShaft extends SubsystemBase {
  
  private final CANSparkMax shooter1 = new CANSparkMax(SHOOTER_MASTER, MotorType.kBrushless);
  private final CANSparkMax shooter2 = new CANSparkMax(SHOOTER_SLAVE, MotorType.kBrushless);
 
  private final CANPIDController shooter1PID = shooter1.getPIDController();
  
  private final CANEncoder shooter1Encoder = shooter1.getEncoder();
  private final CANEncoder shooter2Encoder = shooter2.getEncoder();


  public ShooterSameShaft() {

    //shooter2.follow(shooter1, true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runShooter(double speed){

    shooter1PID.setP(5e-5);
    shooter1PID.setI(1e-6);
    shooter1PID.setD(0);
    shooter1PID.setOutputRange(-1, 1);
    
    double setPoint = speed*6000;

    //shooter1PID.setReference(setPoint, ControlType.kVelocity);

    shooter1.set(speed);
    shooter2.set(-speed); //inverted because we are attempting to put two motors on one shaft.

    SmartDashboard.putNumber("Motor 1 Current", shooter1.getOutputCurrent());
    SmartDashboard.putNumber("Motor 2 Current", shooter2.getOutputCurrent());
    SmartDashboard.putNumber("Motor 1 Speed", shooter1Encoder.getVelocity());
    SmartDashboard.putNumber("Motor 2 Speed", shooter2Encoder.getVelocity());

  }
}
