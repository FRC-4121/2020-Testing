/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import static frc.robot.Constants.*;
import static frc.robot.Constants.DrivetrainConstants.*;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WestCoastDrivetrain;

public class DriveWithXboxCommand extends CommandBase {
  
  private final WestCoastDrivetrain drivetrain;
  
  private final XboxController xbox = new XboxController(XBOX_PORT);
  
  public DriveWithXboxCommand(WestCoastDrivetrain drive) {

    drivetrain = drive;
    addRequirements(drivetrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    drivetrain.drive(SPEED_MAX * xbox.getX(Hand.kLeft), SPEED_MAX * xbox.getY(Hand.kLeft), SPEED_MAX * xbox.getX(Hand.kRight), SPEED_MAX * xbox.getY(Hand.kRight));
    
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
