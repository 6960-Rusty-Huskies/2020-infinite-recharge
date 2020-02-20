package com.north6960.subsystems.controlpanel;

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
