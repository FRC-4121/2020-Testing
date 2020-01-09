/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.AutoDriveCommandGroup;
import frc.robot.subsystems.BeltIntakeSubsystem;
import frc.robot.subsystems.GenericDriveTrain;
import frc.robot.subsystems.MecanumDriveTrain;
import frc.robot.subsystems.WestCoastDriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  //Declare public fields
	public static int driveType;

 	//Network tables and entries
  public static NetworkTableInstance dataTableInstance;
  public static NetworkTable navxTable;
  public static NetworkTable visionTable;
  
  public static NetworkTableEntry driveAngle;
  public static NetworkTableEntry gyroYaw;
  public static NetworkTableEntry yVelocity;
  public static NetworkTableEntry xVelocity;
  public static NetworkTableEntry yDisplacement;
  public static NetworkTableEntry xDisplacement;
  public static NetworkTableEntry zeroGyro;
  public static NetworkTableEntry robotStop;
  //public static NetworkTableEntry zeroDisplace;

  //Declare subsystems
  public static GenericDriveTrain drivetrain;
  public static BeltIntakeSubsystem belt;

  //Declare sensors and control inputs
	public static OI oi;

	//Declare commands
  private Command autonomousCommand;
  
  //Declare Smart dashboard chooser
  private SendableChooser<String> autoStyleChooser;
  private SendableChooser<String> autoSideChooser;
  
  //Variables for auto logic
  public String mySide;
  public String myStyle;

   /**
   * Change the I2C port below to match the connection of your color sensor
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a 
   * parameter. The device will be automatically initialized with default 
   * parameters.
   */
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  /**
   * A Rev Color Match object is used to register and detect known colors. This can 
   * be calibrated ahead of time or during operation.
   * 
   * This object uses a simple euclidian distance to estimate the closest match
   * with given confidence range.
   */
  private final ColorMatch m_colorMatcher = new ColorMatch();

  /**
   * Note: Any example colors should be calibrated as the user needs, these
   * are here as a basic example.
   */
  private final  Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);


	/**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
	 */
  @Override
	public void robotInit() {
    
		//Initialize NetworkTables
		dataTableInstance = NetworkTableInstance.getDefault();
		visionTable = dataTableInstance.getTable("vision");
		navxTable = dataTableInstance.getTable("navx");

		//Initialize NetworkTable entries
		robotStop = visionTable.getEntry("RobotStop");
    
    
    driveAngle = navxTable.getEntry("GyroAngle");
    gyroYaw = navxTable.getEntry("GyroYaw");
		yVelocity = navxTable.getEntry("YVelocity");
		xVelocity = navxTable.getEntry("XVelocity");
		yDisplacement = navxTable.getEntry("YDisplacement");
		xDisplacement = navxTable.getEntry("XDisplacement");
    zeroGyro = navxTable.getEntry("ZeroGyro");
    //zeroDisplace = navxTable.getEntry("ZeroDisplace");

		//Initialize NetworkTable values
    robotStop.setDouble(0.0);
    zeroGyro.setDouble(0.0);
    //zeroDisplace.setDouble(0.0);
    
    //Declare drive configuration
		/* Allows for simple alteration of drive train type.  
    * 1: West Coast
    * 2: Mecanum 
    * 2019 default: Mecanum
    */

		driveType = 1;
	  
    //Initialize the proper drive train based on drive type
    switch(driveType) {
		
      case 1: 
        drivetrain = new WestCoastDriveTrain();
        break;
			
      case 2:
        drivetrain = new MecanumDriveTrain();
        break;
			
      default:
        drivetrain = new MecanumDriveTrain();
		
    }
    
    //Init output-input systems and other subsystems
    belt = new BeltIntakeSubsystem();
    oi = new OI();

    //Initialize dashboard choosers
    autoSideChooser = new SendableChooser<>();
    autoStyleChooser = new SendableChooser<>();
    
    autoSideChooser.addOption("Left", "Left");
    autoSideChooser.addOption("Center", "Center");
    autoSideChooser.addOption("Right", "Right");
    
    autoStyleChooser.setDefaultOption("Full Auto", "Auto");
    autoStyleChooser.addOption("Driver Assist", "Sandstorm");

    SmartDashboard.putData("Auto Side", autoSideChooser);
    SmartDashboard.putData("Auto Style", autoStyleChooser); 
    
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget); 
	}


  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    /**
     * The method GetColor() returns a normalized color value from the sensor and can be
     * useful if outputting the color to an RGB LED or similar. To
     * read the raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in
     * well lit conditions (the built in LED is a big help here!). The farther
     * an object is the more light from the surroundings will bleed into the 
     * measurements and make it difficult to accurately determine its color.
     */
    Color detectedColor = m_colorSensor.getColor();
    System.out.println(detectedColor.toString());

    /**
     * Run the color match algorithm on our detected color
     */
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {

  }


  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }


  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    
    zeroGyro.setDouble(1.0);
    //zeroDisplace.setDouble(1.0);

    myStyle = autoStyleChooser.getSelected();
    mySide = autoSideChooser.getSelected();

    autonomousCommand = null;

    // //Logic tree for choosing our auto program
    // if(myStyle.equals("Sandstorm"))
    // {
    //   autonomousCommand = null;
    // } 
    // else 
    // {
    //   if(mySide.equals("Left"))
    //   {
    //     if(myTarget.equals("Front"))
    //     {
    //       autonomousCommand = new AutoRobotLeftCargoFront();
    //     } 
    //     else if(myTarget.equals("Side"))
    //     {
    //       autonomousCommand = new AutoRobotLeftCargoSide();
    //     }
    //     else
    //     {
    //       autonomousCommand = new AutoDefaultStraight();
    //     }
    //   }
    //   else if(mySide.equals("Right"))
    //   {
    //     if(myTarget.equals("Front"))
    //     {
    //       autonomousCommand = new AutoRobotRightCargoFront();
    //     }
    //     else if(myTarget.equals("Side"))
    //     {
    //       autonomousCommand = new AutoRobotRightCargoSide();
    //     }
    //     else
    //     {
    //       autonomousCommand = new AutoDefaultStraight();
    //     }
    //   }
    //   else //Center starting position; may add some more options here in the future
    //   {
    //     autonomousCommand = new AutoDefaultStraight();
    //   }
    // }

    autonomousCommand = new AutoDriveCommandGroup();

    // schedule the autonomous command
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }


  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }


  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }

    //Zero gyro and displacement
    robotStop.setDouble(0.0);
    //zeroGyro.setDouble(1.0);
    //zeroDisplace.setDouble(1.0);

  }


  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    int POVAxis = Robot.oi.rightJoy.getPOV();
    System.out.println(POVAxis);

  }

  
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
