/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Shifter;
import frc.robot.subsystems.WestCoastDrivetrain;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoTurn;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html

public class AutoTestGroup1 extends SequentialCommandGroup {
  /**
   * Creates a new AutoTestGroup1.
   */
  public AutoTestGroup1(WestCoastDrivetrain drive, Shifter shifter) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

    super(new ShiftDown(shifter),
          //new AutoTurn(drive, shifter, -40, 2),
          new AutoDrive(drive, shifter, 100, -45, -1, 10, true),
          //new AutoTurn(drive, shifter, 0, 1.5),
          new AutoDrive(drive, shifter, 120, 0, -1, 10, false));
  }
}
