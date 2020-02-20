package com.north6960.utils;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

/**
 * The colors on the Control Panel.
 */
public abstract class WheelColor {
  public static final Color blue = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public static final Color green = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static final Color red = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public static final Color yellow = ColorMatch.makeColor(0.361, 0.524, 0.113);
}
