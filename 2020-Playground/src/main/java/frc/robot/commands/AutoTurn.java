/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import static frc.robot.Constants.*;
import static frc.robot.Constants.DrivetrainConstants.*;
import frc.robot.extraClasses.PIDControl;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shifter;
import frc.robot.subsystems.WestCoastDrivetrain;

public class AutoTurn extends CommandBase {
  
  private final WestCoastDrivetrain drivetrain;
  private final Shifter shifter;

  private double targetAngle;
  private double stopTime;

  private double angleCorrection;
  private double startTime;

  private Timer timer = new Timer();

  private PIDControl pidTurn; 

  public AutoTurn(WestCoastDrivetrain drive, Shifter shift, double ang, double time) {

    drivetrain = drive;
    shifter = shift;
    addRequirements(drivetrain, shifter);

    targetAngle = ang;
    stopTime = time;

    pidTurn= new PIDControl(kP_Angle_Turn, kI_Angle_Turn, kD_Angle_Turn);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    timer.start();
    startTime = timer.get();

    angleCorrection = 0;

    shifter.shiftDown();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    angleCorrection = pidTurn.run(drivetrain.getGyroAngle(), targetAngle);
    
    if (angleCorrection > 1.0) {
      angleCorrection = 1.0;
    } else if (angleCorrection < -1.0) {
      angleCorrection = -1.0;
    }

    drivetrain.autoDrive(AUTO_DRIVE_SPEED * angleCorrection, AUTO_DRIVE_SPEED * -angleCorrection);
    SmartDashboard.putNumber("Angle Correction", angleCorrection);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.autoStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    boolean thereYet = false;

    double time = timer.get();

    if(stopTime <= time - startTime){

        thereYet = true;
    }
    else {

      if (Math.abs(targetAngle - drivetrain.getGyroAngle()) <= 2){

        thereYet = true;

      }

    }

    return thereYet;

  }
}
