/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.extraClasses;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Add your docs here.
 */
public class UltrasonicSensor {
    
  private final AnalogInput sensor;

  public UltrasonicSensor(int port){

    sensor = new AnalogInput(port);
  }

  public double getDistance(){

    return sensor.getVoltage()/(.00977 * 2.54);
  }

}
