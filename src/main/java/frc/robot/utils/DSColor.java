/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The color currently 
 */
public abstract class DSColor {
    public static Color getCurrent() {
        switch(DriverStation.getInstance().getGameSpecificMessage()) {
            case "R":
                return WheelColor.red;
            case "G":
                return WheelColor.green;
            case "B":
                return WheelColor.blue;
            case "Y":
                return WheelColor.yellow;
            default:
                return null;
        }
    }
}
