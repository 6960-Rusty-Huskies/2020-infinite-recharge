package com.north6960.subsystems.controlpanel;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Class for interfacing with the colors on the Control Panel. Used for position control.
 */
public abstract class WheelColor {
  public static final Color blue = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public static final Color green = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static final Color red = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public static final Color yellow = ColorMatch.makeColor(0.361, 0.524, 0.113);
  
  public enum ColorEnum {
      red, green, blue, yellow
  }

  public static Color getFMSDisplayed() {
    switch(DriverStation.getInstance().getGameSpecificMessage()) {
        case "R":
            return red;
        case "G":
            return green;
        case "B":
            return blue;
        case "Y":
            return yellow;
        default:
            return null;
    }
  }
}
