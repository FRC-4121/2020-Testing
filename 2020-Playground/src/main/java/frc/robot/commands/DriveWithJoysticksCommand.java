/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WestCoastDrivetrain;

public class DriveWithJoysticksCommand extends CommandBase {
  private final WestCoastDrivetrain drivetrain;

  private final Joystick leftJoy = new Joystick(LEFT_JOY_PORT);
  private final Joystick rightJoy = new Joystick(RIGHT_JOY_PORT);
  
  public DriveWithJoysticksCommand(WestCoastDrivetrain drive) {

    drivetrain = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    drivetrain.drive(leftJoy.getX(), leftJoy.getY(), rightJoy.getX(), rightJoy.getY());
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
