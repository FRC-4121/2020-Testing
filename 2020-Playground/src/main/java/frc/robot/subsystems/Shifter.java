/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shifter extends SubsystemBase {
  
  private final Compressor compressor = new Compressor();

  private final DoubleSolenoid leftShifterSolenoid = new DoubleSolenoid(LEFT_SHIFTER_SOLENOID[0], LEFT_SHIFTER_SOLENOID[1]);
  private final DoubleSolenoid rightShifterSolenoid = new DoubleSolenoid(RIGHT_SHIFTER_SOLENOID[0], RIGHT_SHIFTER_SOLENOID[1]);

  private String gear;

  public Shifter() {

    //Start in low gear
    shiftDown();
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler 
    SmartDashboard.putString("Gear", gear);
  }

  public void shiftUp() {

    leftShifterSolenoid.set(Value.kForward);
    rightShifterSolenoid.set(Value.kForward);
    gear = "High";
  }

  public void shiftDown() {

    leftShifterSolenoid.set(Value.kReverse);
    rightShifterSolenoid.set(Value.kReverse);
    gear = "Low";

  }

  public String getGear(){

    return gear;
  }
}