package com.north6960.lights;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants;
import com.north6960.controlpanel.WheelColor;

/**
 * The RGB on the robot, which uses Rev Blinkin LED controllers and 1-meter individually programmable strips.
 */
public class RGB extends SubsystemBase {

  public AddressableLEDStrip strip;

  public RGB(int port, int numPixels) {
    strip = new AddressableLEDStrip(port, numPixels);

  public void set(WheelColor.ColorEnum color) {
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
    
    strip.setSolid(new Color8Bit(r, g, b));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    strip.update();
  }
}
