/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.*;
import static frc.robot.Constants.DrivetrainConstants.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.extraClasses.CameraThreader;
import frc.robot.extraClasses.NetworkTableQuerier;
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
  private final ShooterSameShaft shooter = new ShooterSameShaft();
  private final Shifter shifter = new Shifter();
  //private final Turret turret = new Turret();
  private final GenericSubsystem genericSubsystem = new GenericSubsystem();

  private final DriveWithXboxCommand xboxCommand = new DriveWithXboxCommand(drivetrain);
  private final GenericJoysticksCommand genericJoysticksCommand = new GenericJoysticksCommand(genericSubsystem);
  private final ShootWithJoysticksCommand shootCommand = new ShootWithJoysticksCommand(shooter);
  private final SpinTurret turretCommand = new SpinTurret(shooter);
  private final Shift shift = new Shift(shifter);
  private final OperateIntake operateIntake = new OperateIntake(shifter);
  private final TestIntake in = new TestIntake(genericSubsystem, -.5);
  private final TestIntake out = new TestIntake(genericSubsystem, 1.0);
  private final TestIntake stopIntake = new TestIntake(genericSubsystem, 0);

  private final CameraServer camServer;
  private final UsbCamera driveCam;

  private final Joystick leftJoy;
  private final Joystick rightJoy;
  private final XboxController xbox;

  private JoystickButton inButton;
  private JoystickButton outButton;
  private JoystickButton shiftButton;
  private JoystickButton invertDirection;
  private JoystickButton extendRetractPneumaticIntake;
  private JoystickButton extend;
  private JoystickButton retract;


  private final NetworkTableQuerier ntables = new NetworkTableQuerier();
  // private final CameraThreader cameraThreader = new CameraThreader();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    camServer = CameraServer.getInstance();
    driveCam = camServer.startAutomaticCapture("Driver View", 0);
    driveCam.setResolution(160, 120);
    driveCam.setFPS(15);
    driveCam.setBrightness(50);
    // cameraThreader.start();

    leftJoy = new Joystick(LEFT_JOY_PORT);
    rightJoy = new Joystick(RIGHT_JOY_PORT);
    xbox = new XboxController(XBOX_PORT);

    //Set default drivetrain command to DriveWithJoysticks or xbox
    drivetrain.setDefaultCommand(xboxCommand);
    
    //For testing purposes, this will control simple one- or two-motor subsystems.
    //genericSubsystem.setDefaultCommand(genericJoysticksCommand);
    
    //shooter.setDefaultCommand(shootCommand);
    shooter.setDefaultCommand(turretCommand);


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
    
    inButton = new JoystickButton(xbox, 5);
    outButton = new JoystickButton(xbox, 6);

    shiftButton = new JoystickButton(xbox, 1);
    extendRetractPneumaticIntake = new JoystickButton(rightJoy, 1);
    invertDirection = new JoystickButton(xbox, 2);

    retract = new JoystickButton(rightJoy, 7);
    extend = new JoystickButton(rightJoy, 8);
    

    shiftButton.whenPressed(shift);
    extendRetractPneumaticIntake.whenPressed(operateIntake);
    invertDirection.whenPressed(new InstantCommand(drivetrain::invertDirection, drivetrain));
    retract.whenPressed(new InstantCommand(shifter::retractIntake, shifter));
    extend.whenPressed(new InstantCommand(shifter::extendIntake, shifter));

    inButton.whileHeld(in);
    inButton.whenReleased(stopIntake);
    outButton.whileHeld(out);
    outButton.whenReleased(stopIntake);


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //boolean gearChoice = SmartDashboard.getBoolean("High Gear?", false);
    return new AutoTestGroup1(drivetrain, shifter);
  }
}
