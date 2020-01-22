/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.LEFT_JOY_PORT;
import static frc.robot.Constants.RIGHT_JOY_PORT;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final WestCoastDrivetrain drivetrain = new WestCoastDrivetrain();
  private final EndEffector end = new EndEffector();
  //private final Shooter shooter = new Shooter();
  private final ShooterSameShaft shooter = new ShooterSameShaft();
  private final Shifter shifter = new Shifter();

  private final DriveWithJoysticksCommand joysticksCommand = new DriveWithJoysticksCommand(drivetrain);
  //private final ShootWithJoysticksCommand shootCommand = new ShootWithJoysticksCommand(shooter);
  private final RunEndEffectorIn runEndEffectorIn = new RunEndEffectorIn(end);
  private final RunEndEffectorOut runEndEffectorOut = new RunEndEffectorOut(end);
  private final StopEndEffector stopEndEffector = new StopEndEffector(end);
  private final ShiftUp shiftUp = new ShiftUp(shifter);
  private final ShiftDown shiftDown = new ShiftDown(shifter);

  private final Joystick leftJoy;
  private final Joystick rightJoy;
  //public final Joystick turretJoy;

  private JoystickButton inButton;
  private JoystickButton outButton;
  private JoystickButton shiftUpButton;
  private JoystickButton shiftDownButton;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    leftJoy = new Joystick(LEFT_JOY_PORT);
    rightJoy = new Joystick(RIGHT_JOY_PORT);
    //turretJoy = new Joystick(2);

    //Set default drivetrain command to DriveWithJoysticks
    drivetrain.setDefaultCommand(joysticksCommand);
    //shooter.setDefaultCommand(shootCommand);


    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    inButton = new JoystickButton(leftJoy, 1);
    outButton = new JoystickButton(rightJoy, 1);

    shiftUpButton = new JoystickButton(rightJoy, 2);
    shiftDownButton = new JoystickButton(leftJoy, 2);

    inButton.whileHeld(runEndEffectorIn);
    inButton.whenReleased(stopEndEffector);

    outButton.whileHeld(runEndEffectorOut);
    outButton.whenReleased(stopEndEffector);

    shiftUpButton.whenPressed(shiftUp);
    shiftDownButton.whenPressed(shiftDown);


  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
