package com.north6960.lights;

import com.north6960.Constants;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The RGB on the robot, which uses Rev Blinkin LED controllers and 1m individually programmable strips.
 */
public class RGB extends SubsystemBase {

  private Spark blinkin;

  public RGB() {
    blinkin = new Spark(Constants.BLINKIN_ID);
  }

  public void setPattern(double id) {
    blinkin.set(id);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
