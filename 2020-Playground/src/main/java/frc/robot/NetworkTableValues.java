/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class NetworkTableValues {

    public static NetworkTableInstance networkTableInstance;
    public static NetworkTable visionTable;
    public static NetworkTable navxTable;

    public static NetworkTableEntry robotStop;
    public static NetworkTableEntry writeVideo;
    public static NetworkTableEntry ballX;
    public static NetworkTableEntry ballY;
    public static NetworkTableEntry ballRadius;
    public static NetworkTableEntry ballDistance;
    public static NetworkTableEntry ballAngle;
    public static NetworkTableEntry ballOffset;
    public static NetworkTableEntry ballScreenPercent;
    public static NetworkTableEntry foundBall;

    public NetworkTableValues(){

        initNetworkTables();
    }

    private void initNetworkTables(){

        networkTableInstance = NetworkTableInstance.getDefault();
        visionTable = networkTableInstance.getTable("vision");
        //navxTable = networkTableInstance.getTable("navx");
    
        robotStop = visionTable.getEntry("RobotStop");
        writeVideo = visionTable.getEntry("WriteVideo");
        ballX = visionTable.getEntry("BallX");
        ballY = visionTable.getEntry("BallY");
        ballRadius = visionTable.getEntry("BallRadius");
        ballDistance = visionTable.getEntry("BallDistance");
        ballAngle = visionTable.getEntry("BallAngle");
        ballOffset = visionTable.getEntry("BallOffset");
        ballScreenPercent = visionTable.getEntry("BallScreenPercent");
        foundBall = visionTable.getEntry("FoundBall");
    
      }


}
