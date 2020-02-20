/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.north6960.subsystems.generatorswitch;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants;

/**
 * Used to level the robot to help balance on the Generator Switch.
 */
public class Winch extends SubsystemBase {

  private VictorSPX motorControler;

  public Winch() {
    motorControler = new VictorSPX(Constants.WINCH_MOTOR);
  }

  public void move(double speed) {
    motorControler.set(VictorSPXControlMode.Velocity, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
