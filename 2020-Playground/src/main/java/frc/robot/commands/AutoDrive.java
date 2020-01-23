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

public class AutoDrive extends CommandBase {
  
  private final WestCoastDrivetrain drivetrain;
  private final Shifter shifter;

  private double targetDistance;
  private double targetAngle;
  private double direction;
  private double stopTime;
  private boolean highGear;

  private double angleCorrection, angleError;
  private double startTime;
  private double distanceTraveled;

  private int leftEncoderStart;
  private int rightEncoderStart;


  private Timer timer = new Timer();
  private PIDControl pidControl;


  public AutoDrive(WestCoastDrivetrain drive, Shifter shift, double dis, double ang, double dir, double time, boolean useHighGear) {

    drivetrain = drive;
    shifter = shift;
    addRequirements(drivetrain, shifter);

    targetDistance = dis;
    targetAngle = ang;
    direction = dir;
    stopTime = time;
    highGear = useHighGear;

    pidControl = new PIDControl(kP_Straight, kI_Straight, kD_Straight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    distanceTraveled = 0.0;
    timer.start();
    startTime = timer.get();

    leftEncoderStart = drivetrain.getLeftEncoder();
    rightEncoderStart = drivetrain.getRightEncoder();

    angleCorrection = 0;
    angleError = 0;

    if(highGear && shifter.getGear().equals("Low"))
      shifter.shiftUp();
    else if (!highGear && shifter.getGear().equals("High")) 
      shifter.shiftDown();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    angleCorrection = pidControl.run(drivetrain.getGyroAngle(), targetAngle);
    drivetrain.autoDrive(direction*AUTO_DRIVE_SPEED + angleCorrection, direction*AUTO_DRIVE_SPEED - angleCorrection);
    SmartDashboard.putNumber("Angle Correction", angleCorrection);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
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

      int totalRotationsRight = Math.abs((drivetrain.getRightEncoder() - rightEncoderStart) / 4096);
      int totalRotationsLeft = Math.abs((drivetrain.getLeftEncoder() - leftEncoderStart) / 4096);
      
      if (highGear)
        distanceTraveled = (WHEEL_DIAMETER * Math.PI * (totalRotationsLeft + totalRotationsRight) / 2.0) / DRIVE_GEAR_RATIO_HIGH;
      else
        distanceTraveled = (WHEEL_DIAMETER * Math.PI * (totalRotationsLeft + totalRotationsRight) / 2.0) / DRIVE_GEAR_RATIO_LOW;

      if(targetDistance <= distanceTraveled){

        thereYet = true;
      }

      SmartDashboard.putNumber("Distance Traveled", distanceTraveled);

    }

    return thereYet;

  }
}
