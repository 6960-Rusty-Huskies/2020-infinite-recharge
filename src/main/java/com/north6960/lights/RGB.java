package com.north6960.lights;

import com.north6960.controlpanel.WheelColor.ColorEnum;

import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The RGB on the robot, which uses 1-meter individually programmable strips to display patterns.
 */
public class RGB extends SubsystemBase {

  private AddressableLEDStrip strip;

  public RGB(int port, int length) {
    strip = new AddressableLEDStrip(port, length);
  }

  public void setToWheelColor(ColorEnum color) {
    int r = 0, g = 0, b = 0;

    switch(color) {
      case red:
        r = 255;
        break;
      case green:
        g = 255;
        break;
      case blue:
        b = 255;
        break;
      case yellow:
        r = 255;
        g = 255;
        break;
    }
    
    strip.setSolid(r, g, b);
  }

  public void shiftPatternUp() {
    strip.shiftPatternUp();
  }

  public void shiftPatternDown() {
    strip.shiftPatternDown();
  }

  public void setGradient(int width, Color8Bit... colors) {
    strip.setGradient(width, colors);
  }

  public void setAlternating(int width, Color8Bit... colors) {
    strip.setAlternating(width, colors);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    strip.update();
  }
}
