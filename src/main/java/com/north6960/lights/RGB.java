package com.north6960.lights;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The RGB on the robot, which uses Rev Blinkin LED controllers and 1-meter individually programmable strips.
 */
public class RGB extends SubsystemBase {

  public AddressableLEDStrip strip;

  public RGB(int port, int numPixels) {
    strip = new AddressableLEDStrip(port, numPixels);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    strip.update();
  }
}
