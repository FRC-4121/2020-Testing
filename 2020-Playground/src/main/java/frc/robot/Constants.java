/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //Talon IDs for all subsystems
    //Single generic controller for all types
    public static final int GENERIC_TALON_1 = 5;
    public static final int GENERIC_FALCON_1 = -1;
    public static final int GENERIC_SPARK_1 = 1;
    
    public static final int GENERIC_TALON_2 = -1;
    public static final int GENERIC_FALCON_2 = -1;
    public static final int GENERIC_SPARK_2 = -1;
    
    //Shooter Talons
    public static final int SHOOTER_SLAVE = -1;
    public static final int SHOOTER_MASTER = -1;
    public static final int TURRET = -1;

    //Drivetrain Talons
    public static final int MASTER_LEFT_MOTOR = 7;
    public static final int SLAVE1_LEFT_MOTOR = 6;
    public static final int SLAVE2_LEFT_MOTOR = 4;
    public static final int MASTER_RIGHT_MOTOR = 3;
    public static final int SLAVE1_RIGHT_MOTOR = 2;
    public static final int SLAVE2_RIGHT_MOTOR = 8;
    
    //Chassis #2
    // public static final int MASTER_LEFT_MOTOR = 4;
    // public static final int SLAVE1_LEFT_MOTOR = 2;
    // public static final int MASTER_RIGHT_MOTOR = 0;
    // public static final int SLAVE1_RIGHT_MOTOR = 1;

    //Other Talons
    public static final int CLIMBER_MOTOR = -1;
    public static final int PROCESSOR_1 = -1;
    
    //Joystick port IDs
    public static final int LEFT_JOY_PORT = 0;
    public static final int RIGHT_JOY_PORT = 1;
    public static final int XBOX_PORT = 2;

    public static class ShooterConstants {

        //PID constants
        public static final double kP_Shoot = 0;
        public static final double kI_Shoot = 0;
        public static final double kD_Shoot = 0; 
        public static final double kP_Turret = .04;
        public static final double kI_Turret = 0;
        public static final double kD_Turret = 0.00;

        //Encoders
        public static final int TURRET_ENCODER_1 = 0;
        public static final int TURRET_ENCODER_2 = 1;

        //Turret slow-down factor
        public static final double kTurret_Speed = .5;

        //Configuration constants
        public static final double kRPS_Tolerance = 0;
        public static final double kRPS_Target = 100;
        public static final int kPIDLoopIdx = 0;
        public static final int kTimeoutMs = 20;

        public static final double kShooterWheelDiameter = 6; //inches
        public static final double kEncoderPPR = 4096; //pulses per revolution
        public static final double kTalonFXPPR = 2048;
        public static final double kTurretEncoderPPR = 7;
        public static final double kEncoderDistancePerPulse = kShooterWheelDiameter * Math.PI / kEncoderPPR; 

        public static final double kShooterSpeed = 1.0;

        public static final double TURRET_SPEED = .8;
        
    }

    public static class DrivetrainConstants {

        //Speeds
        public static final double AUTO_DRIVE_SPEED = 0.9;

        //Encoder and PID config constants
        public static final int kPIDLoopIdx = 0;
        public static final int kTimeoutMs = 20;
        public static final boolean kSensorPhase = true;
        public static final boolean kMotorInvert = false; //right side is inverted compared to left already, change this to swap sides
        public static final double WHEEL_DIAMETER = 7.5;
        public static final double DRIVE_GEAR_RATIO_HIGH = 7.08;
        public static final double DRIVE_GEAR_RATIO_LOW = 15.32;
        public static final double LOW_GEAR_FUDGE_FACTOR = .288;
        public static final double HIGH_GEAR_FUDGE_FACTOR = .33;
        public static final double AUTO_ENCODER_REVOLUTION_FACTOR = 22187.5;

        //PID Constants
        public static final double kP_Angle_Straight = 0.032;
        public static final double kI_Angle_Straight = 0;
        public static final double kD_Angle_Straight = 0;

        public static final double kP_Angle_Turn = .5;
        public static final double kI_Angle_Turn = 0;
        public static final double kD_Angle_Turn = 0;

        public static final double kP_Speed_Low = 0.07;
        public static final double kI_Speed_Low = 0;
        public static final double kD_Speed_Low = 0.0027;

        public static final double kP_Speed_High = 0.0255;
        public static final double kI_Speed_High = 0;
        public static final double kD_Speed_High = 0.0037;

        //Shifter solenoid IDs
        public static final int[] LEFT_SHIFTER_SOLENOID = {4, 5};
        public static final int[] RIGHT_SHIFTER_SOLENOID = {2, 3};
        public static final int[] INTAKE_SOLENOID = {0, 1};

        //Miscellaneous
        public static int DIRECTION_MULTIPLIER = -1;
        public static final double SPEED_MAX = 0.8;



    }

    public static class ClimberConstants {

    }
}
