package com.north6960.lights;

import com.north6960.controlpanel.WheelColor;

import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The RGB on the robot, which uses 1-meter individually programmable strips to display patterns.
 */
public class RGB extends SubsystemBase {

  RGB rgb = new RGB(0, 120);

  public AddressableLEDStrip strip;

  public RGB(int port, int numPixels) {
    strip = new AddressableLEDStrip(port, numPixels);
  }

  public void setToWheelColor(WheelColor.ColorEnum color) {
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    Color8Bit[] colors = new Color8Bit[]{ new Color8Bit(255, 0, 0), new Color8Bit(0, 0, 255) };
  
    Command altCmd = new ShiftPattern(rgb);
    
    rgb.strip.setAlternating(5, colors);
    rgb.setDefaultCommand( new RGBPattern(altCmd, 60) );
    strip.update();
  }
}
