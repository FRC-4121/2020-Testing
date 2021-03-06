/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import static frc.robot.Constants.DrivetrainConstants.*;
import static frc.robot.Constants.ShooterConstants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.extraClasses.NetworkTableQuerier;
import frc.robot.extraClasses.PIDControl;
import frc.robot.subsystems.Turret;

public class TurretTargetLock extends CommandBase {
  
  private final Turret turret;
  private final NetworkTableQuerier ntables;

  private double angleCorrection;
  private double ballAngle;
  
  private PIDControl pidAngle;

  public TurretTargetLock(Turret turretSystem, NetworkTableQuerier ntable) {

    turret = turretSystem;
    ntables = ntable;

    addRequirements(turret);

    pidAngle = new PIDControl(kP_Turret, kI_Turret, kD_Turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    angleCorrection = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (turret.getTargetLock() && ntables.getVisionBoolean("FoundBall")){
      
      ballAngle = ntables.getVisionDouble("BallAngle");
      angleCorrection = pidAngle.run(ballAngle, 0);

      if(Math.abs(ballAngle) <= 2){
        angleCorrection = 0;
      }

      //Actual turret code is being implemented at this time
      //turret.autoDrive(TURRET_SPEED * -angleCorrection, TURRET_SPEED * angleCorrection);
    
    } 

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
