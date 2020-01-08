/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoDriveCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoDriveCommandGroup() {
    
    //addSequential(new AutoDrive(45, 45, 3.0, 0.625));
    addSequential(new AutoRotate(45, .5, 0.375));
    addSequential(new AutoDrive(90, 45, 3.0, 0.625));
    // addSequential(new AutoRotate(180, .5, 0.375));
    // addSequential(new AutoDrive(90, 180, 3.0, 0.625));
    // addSequential(new AutoRotate(180, 0.75, 0.375));
    // addSequential(new AutoDrive(-90, 180, 1.5, 0.625));
    //addSequential(new AutoRotate(-90, 0.5, 0.375));
    //addSequential(new AutoDrive(90, -90, 1.5, 1.0));
    //addSequential(new AutoRotate(0, 0.5, 0.375));
    //addSequential(new AutoDrive(90, 0, 0.75, 1.0));

  }
}
