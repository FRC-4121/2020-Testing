/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.extraClasses;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * Add your docs here.
 */
public class CameraThreader implements Runnable {

    private final CameraServer camServer;
    private final UsbCamera ballCamera;
    private final UsbCamera shootCamera;

    //UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();

    // private final CvSink ballSink;
    // private final CvSink shootSink;
    // private final CvSource ballOutputStream;
    // private final CvSource shootOutputStream;

    //Mat source = new Mat();
    //Mat output = new Mat();

    public CameraThreader(){

        camServer = CameraServer.getInstance();
        ballCamera = camServer.startAutomaticCapture("Ball Cam", 0);
        ballCamera.setResolution(160, 120);
        ballCamera.setFPS(15);
        ballCamera.setBrightness(50);

        //ballSink = camServer.getVideo();
        //ballOutputStream = camServer.putVideo("Ball", 640, 480);

        shootCamera = camServer.startAutomaticCapture("Shooter Cam", 1);
        shootCamera.setResolution(160, 120);
        shootCamera.setFPS(15);
        shootCamera.setBrightness(50);

    }

    public void run(){

        while(!Thread.interrupted()) {
            // if (cvSink.grabFrame(source) == 0) {
            //   continue;
            // }
            // Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
            // outputStream.putFrame(output);
        }
    }

    public void start(){

        Thread cameraThread = new Thread(this);
        cameraThread.start();
    }
}
