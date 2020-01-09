/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveWithJoysticksCommand;

/**
 * The general drive train class for all various drive train styles. Methods are
 * inherited and overridden by the individual DT classes. All methods present in
 * any DT class should also exist here.
 */

public class GenericDriveTrain extends Subsystem {

    
    //Specific a default command
    public void initDefaultCommand() {
        
    	//All drive trains have default command to drive with the joysticks
        setDefaultCommand(new DriveWithJoysticksCommand());
    }
    
    //Method for driving during Teleop - used for WCD
    public void drive(double leftJoyX, double leftJoyY, double rightJoyX, double rightJoyY) {}
    
    public void drive(double rightJoyX, double rightJoyY, double rightJoyZ, boolean useGyro) {}

    //Method for driving during Autonomous - used for WCD
    public void autoDrive(double leftSpeed, double rightSpeed) {}

    //Method for driving during Autonomous - used for Mecanum
    public void autoDrive(double speed, double angle, double rotation) {}

}
