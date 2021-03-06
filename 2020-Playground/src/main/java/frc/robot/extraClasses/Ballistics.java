package frc.robot.extraClasses;

/*
 * A class designed to calculate the optimal speed and angle for firing a relatively light missile 
 * to a set height from a given distance, using a single-flywheel mechanism.
 * 
 * Originally designed for FRC #4121 Viking Robotics, 2020 Season
 * 
 * @author: Jonas Muhlenkamp
 */

import static java.lang.Math.*;

import java.util.Scanner;

public class Ballistics {

    //Input values
    public double destinationHeight;
    public double launcherHeight;
    public double heightTolerance;
    public double maxSpeedRPM;
    public double wheelDiameter;
    public double wheelSlip;

    //Constants
    public final double g = 386.04;//acceleration due to gravity, in/s^2

    //Math-based values
    public double targetHeight;
    public double wheelCircumference;
    public double missileMaxSpeed;

    //Ballistics array configuration values
    public static int minDistance = 2;//in feet
    public static int maxDistance = 40;//in feet
    public int distanceIncrement = 1;//not recommended to change this value
    
    //Can be configured for a 'continuous' angle or a two-angle system
    public int minAngle = 44;//in degrees
    public int maxAngle = 45;//in degrees
    public int angleIncrement = 1;//make sure this is a factor of the difference between min and max angle
    
    public int minSpeed = 70;//percent
    public int maxSpeed = 100;//percent
    public int argumentCount = 5;//distance, angle, speed, height, possibility (0 is false)

    /* 2-d ballistics array
     * Structure:  each internal array is [distance, angle, speed, height, possibility of shot]
     */ 
    public static double[][] ballisticsTable;


    /*
     * Class constructor
     * @param targetH: The height, in inches, of the desired target above the ground
     * @param launchH: The height, in inches, of the launcher release point above the ground
     * @param tolerance: A additive factor, in inches, that widens the range to target around the target height 
     * @param maxRPM: The maximum speed, in rotations per minute, of the flywheel
     * @param wheelD: The diameter, in inches, of the flywheel
     * @param slip: The percent of wheel speed that is converted to linear speed of the missile (due to the single-wheel design, much is lost as rotational speed)
     */
    public Ballistics(double targetH, double launchH, double tolerance, double maxRPM, double wheelD, double slip){

        //Initiailize class values
        destinationHeight = targetH;
        launcherHeight = launchH;
        heightTolerance = tolerance;
        maxSpeedRPM = maxRPM;
        wheelDiameter = wheelD;
        wheelSlip = slip;

        //Calculate needed height from shot
        targetHeight = destinationHeight - launcherHeight;

        //Calculate missile max speed
        wheelCircumference = wheelDiameter * Math.PI;
        missileMaxSpeed = maxSpeedRPM * wheelCircumference * wheelSlip / 60;//in/s

        System.out.println("Target Height: " + targetHeight + ", Max Speed: " + missileMaxSpeed);

        //Init ballistics table
        ballisticsTable = generateBallisticsTable();

    }

    public double[][] generateBallisticsTable(){

        //Table to be filled and returned
        double[][] table = new double[(maxDistance - minDistance)/distanceIncrement + 1][argumentCount];

        //Start iterating at hardcoded minimum distance
        double startDistance = minDistance;
      
        //Loop through the distances foot by foot
        for(int i = 0; i < table.length; i++){
 
            //At each foot, calculate the optimal angle and speed
            double optimalAngle = 0;
            double optimalSpeed = 0;
            double shotPossible = 0;

            double minError = heightTolerance;
            double bestHeight = 0;
            
            //For each angle...
            for(int a = minAngle; a <= maxAngle; a += angleIncrement){

                //and each speed at each angle...
                for(int s = minSpeed; s <= maxSpeed; s++){

                    //calculate the height that the ball will be at when it hits the wall and error from target
                    double speed = s / 100.0;
                    double height = calculateHeight(startDistance, a, speed);
                    double error = abs(targetHeight - height);

                    //If the error is the smallest yet, assign values of this configuration to place in the table
                    if(error < minError){

                        optimalSpeed = speed;
                        optimalAngle = a;
                        minError = error;
                        bestHeight = height;
                        shotPossible = 1;
                    }

                }

            }

            //Assign table values
            table[i][0] = startDistance;
            table[i][1] = optimalAngle;
            table[i][2] = optimalSpeed;
            table[i][3] = bestHeight;
            table[i][4] = shotPossible;

            //Move to next distance
            startDistance += distanceIncrement;
        }

        return table;
    }

    //Input distance in feet, angle in degrees, speed in percent; output height in inches
    public double calculateHeight(double distance, double angle, double speed){
    
        //Derived from parametric equations of t based on basic trajectories.  Does not account for air resistance; this should be accounted for in the 'slip factor'
        double height = tan(toRadians(angle)) * distance * 12 - 0.5 * g * pow(distance * 12 / (cos(toRadians(angle)) * missileMaxSpeed * speed), 2);

        return height;
    }

    //Actually grab shot configurations from the table
    public static double[] queryBallisticsTable(double distance){

        double[] tableValues = new double[4];

        //Default values
        double shotPossible = 0;//essentially a boolean that I can put into an array of doubles; 0 = false, 1 = true
        double angle = 0;
        double speed = 0;
        double dist = 0;
        double distError = 100;//obscenely large to avoid problems

        //If outside the limits of the mechanism, the shot is not possible and we return immediately
        if(distance < (double) minDistance || distance > (double) maxDistance){

            tableValues[0] = 0;
            tableValues[1] = 0;
            tableValues[2] = 0;
            tableValues[3] = 0;

            return tableValues;
        
        //Otherwise, compare the entered distance to the incremented distances in the table and grab data
        } else {

            for(int i = 0; i < ballisticsTable.length; i++){

                double error = abs(ballisticsTable[i][0] - distance);
                //System.out.println(error);
                //System.out.println(distError);

                if(error < distError){

                    distError = error;
                    shotPossible = ballisticsTable[i][4];
                    angle = ballisticsTable[i][1];
                    speed = ballisticsTable[i][2];
                    dist = ballisticsTable[i][0];
                }
            }

            tableValues[0] = shotPossible;
            tableValues[1] = angle;
            tableValues[2] = speed;
            tableValues[3] = dist;

            return tableValues;

        }
    }


    public static void main(String[] args){

        Ballistics ballistics = new Ballistics(98.25, 24, 5, 6380, 6, .227);

        System.out.println("-----------------------------------------");
        System.out.print("4121 Ballistics Program\n");
                    
        Scanner scIn = new Scanner(System.in);

        System.out.print("Start Calculations? (Y/N) ");

        while(scIn.next().toUpperCase().equals("Y")){

            System.out.print("Please enter a distance in feet: ");
            double distance = scIn.nextDouble();

            System.out.println("Attempting shot at distance of " + distance + " feet.");

            double[] tableQuery = queryBallisticsTable(distance);
            System.out.println("Calculating...");
            
            if(tableQuery[0] == 1){

                System.out.println("Shot possible at distance of " + distance + " feet.");
                System.out.println("Parameters: ");
                System.out.println("    Angle: " + tableQuery[1] + " degrees.");
                System.out.println("    Speed: " + tableQuery[2] + " percent.");
                System.out.println("    Distance used: " + tableQuery[3] + " feet.");
            
            } else {

                System.out.println("Shot not possible at distance of " + distance + " feet.");
            }

            System.out.print("\nContinue Calculations? (Y/N) ");

        }

        scIn.close();
    }

}