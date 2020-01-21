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

    //public static final int END_EFFECTOR_MOTOR = -1;
    //public static final int MASTER_LEFT_MOTOR = 3;
    //public static final int MASTER_RIGHT_MOTOR = 0;
    //public static final int SLAVE_LEFT_MOTOR = 4;
    //public static final int SLAVE_RIGHT_MOTOR = 1;
    public static final int MASTER_LEFT_MOTOR = -1;
    public static final int SLAVE1_LEFT_MOTOR = -1;
    public static final int SLAVE2_LEFT_MOTOR = -1;
    public static final int MASTER_RIGHT_MOTOR = -1;
    public static final int SLAVE1_RIGHT_MOTOR = -1;
    public static final int SLAVE2_RIGHT_MOTOR = -1;

    

    public static int DIRECTION_MULTIPLIER = -1;

    public static int LEFT_JOY_PORT = 0;
    public static int RIGHT_JOY_PORT = 1;

    public static class ShooterConstants {

        //Talon IDs
        public static final int SHOOTER_SLAVE = 1;
        public static final int SHOOTER_MASTER = 2;

        //PID constants
        public static double kP_Shoot = 0;
        public static double kI_Shoot = 0;
        public static double kD_Shoot = 0; 

        //Configuration constants
        public static double kRPS_Tolerance = 0;
        public static final double kRPS_Target = 100;

        public static final double kShooterWheelDiameter = 6; //inches
        public static final double kEncoderPPR = 4096; //pulses per revolution
        public static final double kEncoderDistancePerPulse = kShooterWheelDiameter * Math.PI / kEncoderPPR; 

        public static final double kShooterSpeed = 1.0;
        
    }

    public static class DrivetrainConstants {

        //Talon IDs

        //PID constants
        //public static ;
    }
}
