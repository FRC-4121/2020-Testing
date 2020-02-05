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

  private double angleCorrection, angleError, speedCorrection;
  private double startTime;
  private double distanceTraveled;

  private int leftEncoderStart;
  private int rightEncoderStart;


  private Timer timer = new Timer();
  private PIDControl pidAngle;
  private PIDControl pidSpeedLow;
  private PIDControl pidSpeedHigh; 


  public AutoDrive(WestCoastDrivetrain drive, Shifter shift, double dis, double ang, double dir, double time, boolean useHighGear) {

    drivetrain = drive;
    shifter = shift;
    addRequirements(drivetrain, shifter);

    targetDistance = dis;
    targetAngle = ang;
    direction = dir;
    stopTime = time;
    highGear = useHighGear;

    pidAngle = new PIDControl(kP_Angle_Straight, kI_Angle_Straight, kD_Angle_Straight);
    pidSpeedLow = new PIDControl(kP_Speed_Low, kI_Speed_Low, kD_Speed_Low);
    pidSpeedHigh = new PIDControl(kP_Speed_High, kI_Speed_High, kD_Speed_High);
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
    speedCorrection = 1;

    if(highGear && shifter.getGear().equals("Low"))
      shifter.shiftUp();
    else if (!highGear && shifter.getGear().equals("High")) 
      shifter.shiftDown();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    angleCorrection = pidAngle.run(drivetrain.getGyroAngle(), targetAngle);
    if (highGear) {
      speedCorrection = pidSpeedHigh.run(distanceTraveled, targetDistance);
    }
    else {
      speedCorrection = pidSpeedLow.run(distanceTraveled, targetDistance);
    }
    
    if (speedCorrection > 1.0) {
      speedCorrection = 1.0;
    } else if (speedCorrection < -1.0) {
      speedCorrection = -1.0;
    }
    drivetrain.autoDrive(-speedCorrection * direction * AUTO_DRIVE_SPEED + angleCorrection, -speedCorrection * direction*AUTO_DRIVE_SPEED - angleCorrection);
    SmartDashboard.putNumber("Angle Correction", angleCorrection);
    SmartDashboard.putNumber("Speed Correction", speedCorrection);

    int totalRotationsRight = Math.abs((drivetrain.getRightEncoder() - rightEncoderStart));
    int totalRotationsLeft = Math.abs((drivetrain.getLeftEncoder() - leftEncoderStart));

    if(Math.abs(totalRotationsRight) > 0 && Math.abs(totalRotationsLeft) > 0){
      distanceTraveled = (WHEEL_DIAMETER * Math.PI * (totalRotationsLeft + totalRotationsRight) / 2.0) / AUTO_ENCODER_REVOLUTION_FACTOR;
    } else if(Math.abs(totalRotationsRight) > 0) {
      distanceTraveled = (WHEEL_DIAMETER * Math.PI * (totalRotationsRight)) / AUTO_ENCODER_REVOLUTION_FACTOR;
    } else if(Math.abs(totalRotationsLeft) > 0) {
      distanceTraveled = (WHEEL_DIAMETER * Math.PI * (totalRotationsLeft)) / AUTO_ENCODER_REVOLUTION_FACTOR;
    }

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

    
    if (Math.abs(targetDistance - distanceTraveled) <= 1){

      thereYet = true;

      // else if(Math.abs(targetDistance - distanceTraveled) <= 24){

        //shifter.shiftDown();
      
      //}

      

      }// } else if (stopTime <= time - startTime){

    //   thereYet = true;
    // }
    SmartDashboard.putNumber("Distance Traveled", distanceTraveled);

    return thereYet;

  }
}
