/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
  // private final Shooter shooter = new Shooter();
  private final ShooterSameShaft shooter = new ShooterSameShaft();
  private final Shifter shifter = new Shifter();
  private final Turret turret = new Turret();
  private final GenericSubsystem genericSubsystem = new GenericSubsystem();

  private final DriveWithJoysticksCommand joysticksCommand = new DriveWithJoysticksCommand(drivetrain);
  private final DriveWithXboxCommand xboxCommand = new DriveWithXboxCommand(drivetrain);
  private final GenericJoysticksCommand genericJoysticksCommand = new GenericJoysticksCommand(genericSubsystem);
  private final ShootWithJoysticksCommand shootCommand = new ShootWithJoysticksCommand(shooter);
  private final SpinTurret turretCommand = new SpinTurret(turret);
  private final ShiftUp shiftUp = new ShiftUp(shifter);
  private final ShiftDown shiftDown = new ShiftDown(shifter);

  private final CameraServer camServer;
  private final UsbCamera driveCam;

  private final Joystick leftJoy;
  private final Joystick rightJoy;
  private final XboxController xbox;

  private JoystickButton inButton;
  private JoystickButton outButton;
  private JoystickButton shiftUpButton;
  private JoystickButton shiftDownButton;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    camServer = CameraServer.getInstance();
    driveCam = camServer.startAutomaticCapture("Driver View", 0);
    driveCam.setResolution(160, 120);
    driveCam.setFPS(15);
    driveCam.setBrightness(50);

    leftJoy = new Joystick(LEFT_JOY_PORT);
    rightJoy = new Joystick(RIGHT_JOY_PORT);
    xbox = new XboxController(XBOX_PORT);

    //Set default drivetrain command to DriveWithJoysticks or xbox
    //drivetrain.setDefaultCommand(joysticksCommand);
    drivetrain.setDefaultCommand(xboxCommand);
    
    //For testing purposes, this will control simple one- or two-motor subsystems.
    genericSubsystem.setDefaultCommand(genericJoysticksCommand);
    
    shooter.setDefaultCommand(shootCommand);
    //turret.setDefaultCommand(turretCommand);

    SmartDashboard.putBoolean("High Gear?", false);
    SmartDashboard.putBoolean("Target Lock", false);
    //SmartDashboard.putBoolean("Target Lock", turret.getTargetLock());

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

    shiftUpButton.whenPressed(shiftUp);
    shiftDownButton.whenPressed(shiftDown);


  }

  public boolean getTargetLock(){

    return turret.getTargetLock();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    boolean gearChoice = SmartDashboard.getBoolean("High Gear?", false);
    return new AutoSpin(drivetrain, .4, 5);
  }
}
