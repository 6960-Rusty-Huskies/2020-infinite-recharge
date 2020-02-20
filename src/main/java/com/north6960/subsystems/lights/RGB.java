/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.north6960.subsystems.lights;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants;

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
