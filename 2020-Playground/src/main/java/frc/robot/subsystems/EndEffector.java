/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class EndEffector extends SubsystemBase {
 
  //WPI_TalonSRX endEffectorMotor;
  private CANSparkMax endEffectorMotor;
  
  public EndEffector() {

    //endEffectorMotor = new CANSparkMax(END_EFFECTOR_MOTOR, MotorType.kBrushless);

  }

  public void runMotor(double speed){

    //endEffectorMotor.set(speed);


  }

  private void initMotor(){

  }

}
