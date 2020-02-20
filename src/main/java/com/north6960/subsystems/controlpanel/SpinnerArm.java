/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.north6960.subsystems.controlpanel;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.north6960.Constants;
import com.north6960.utils.Direction;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpinnerArm extends SubsystemBase {

  private double speed;
  private VictorSPX motor;
  private DigitalInput limitSwitch;

  public SpinnerArm() {
    motor = new VictorSPX(Constants.SPINNER_ARM_MOTOR);
    limitSwitch = new DigitalInput(Constants.SPINNER_ARM_LIMIT_SWITCH);
  }
  
  public void move(Direction direction) {
    switch(direction) {
      case up:
        motor.set(VictorSPXControlMode.PercentOutput, -speed);
        break;
      case down:
        motor.set(VictorSPXControlMode.PercentOutput, speed);
        break;
      case stopped:
        motor.set(VictorSPXControlMode.PercentOutput, 0.0);
        break;
      case left:
      case right:
        break;
    }
  }
  
  public boolean limitSwitchTriggered() {
    return limitSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
