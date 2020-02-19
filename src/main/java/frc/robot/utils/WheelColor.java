/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The colors on the Control Panel.
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
