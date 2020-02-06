package frc.robot.extraClasses;

import java.lang.Thread;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class NetworkTableQuerier implements Runnable{

    private static NetworkTableInstance networkTableInstance;
    private static NetworkTable visionTable;
    private static NetworkTable navxTable;

    private static NetworkTableEntry robotStop;
    private static NetworkTableEntry writeVideo;
    private static NetworkTableEntry ballX;
    private static NetworkTableEntry ballY;
    private static NetworkTableEntry ballRadius;
    private static NetworkTableEntry ballDistance;
    private static NetworkTableEntry ballAngle;
    private static NetworkTableEntry ballOffset;
    private static NetworkTableEntry ballScreenPercent;
    private static NetworkTableEntry foundBall;

    public NetworkTableQuerier(){

        initNetworkTables();
    }
    
    public void run(){
        
        while(true){

            queryNetworkTables();
             
        }
    }

    public void start(){

        Thread ntThread = new Thread(this);
        ntThread.start();
    }

    private void initNetworkTables(){

        networkTableInstance = NetworkTableInstance.getDefault();
        visionTable = networkTableInstance.getTable("vision");
        //navxTable = networkTableInstance.getTable("navx");

        queryNetworkTables();

    }


    private void queryNetworkTables(){

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

    /*
     *@param entry The ID of the NetworkTables entry to return
     *@return the double value of the NetworkTables entry chosen; an error will be returned if entry is not a double  
     */

    public static synchronized double getVisionDouble(String entry){

        return visionTable.getEntry(entry).getDouble(0);
    }

    /*
     *@param entry The ID of the NetworkTables entry to return
     *@return the boolean value of the NetworkTables entry chosen; an error will be returned if entry is not a boolean  
     */
    public static synchronized boolean getVisionBoolean(String entry){

        return visionTable.getEntry(entry).getBoolean(false);
    }

}