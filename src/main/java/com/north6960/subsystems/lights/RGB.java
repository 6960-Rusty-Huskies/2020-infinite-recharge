package com.north6960.subsystems.lights;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants;
import com.north6960.subsystems.controlpanel.WheelColor;

/**
 * The RGB on the robot, which uses Rev Blinkin LED controllers and 1-meter individually programmable strips.
 */
public class RGB extends SubsystemBase {

  private Spark blinkin;

  public RGB() {
    blinkin = new Spark(Constants.BLINKIN_ID);
  }

  public void setPattern(double id) {
    blinkin.set(id);
  }

  public void setPattern(WheelColor.ColorEnum color) {
    double id = 0.0;

    switch(color) {
      case red:
        id = 0.61;
        break;
      case green:
        id = 0.77;
        break;
      case blue:
        id = 0.87;
        break;
      case yellow:
        id = 0.69;
        break;
    }

    blinkin.set(id);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
