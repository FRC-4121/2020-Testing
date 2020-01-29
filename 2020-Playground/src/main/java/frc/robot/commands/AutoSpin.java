/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WestCoastDrivetrain;

public class AutoSpin extends CommandBase {
  
  private final WestCoastDrivetrain drivetrain;

  private double spinSpeed;

  private Timer timer;
  private double stopTime;
  private double startTime;

  public AutoSpin(WestCoastDrivetrain drive, double speed, double time) {
  
    drivetrain = drive;
    addRequirements(drivetrain); 

    timer = new Timer();
    
    spinSpeed = speed;
    stopTime = time;
  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    startTime = timer.get();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    drivetrain.autoDrive(spinSpeed, -spinSpeed);
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

    return thereYet;
  }
}
