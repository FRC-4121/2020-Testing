/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveWithJoysticksCommand extends Command {
  
  public DriveWithJoysticksCommand() {
    
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Mecanum: 
    //Robot.drivetrain.drive(-Robot.oi.rightJoy.getY(), Robot.oi.rightJoy.getX(), Robot.oi.rightJoy.getZ(), false);
    
    //West Coast Drive:
    Robot.drivetrain.drive(Robot.oi.leftJoy.getX(), Robot.oi.leftJoy.getY(), 
                            Robot.oi.rightJoy.getX(), Robot.oi.rightJoy.getY());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
