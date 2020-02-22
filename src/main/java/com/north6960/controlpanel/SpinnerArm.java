/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.north6960.controlpanel;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.north6960.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpinnerArm extends SubsystemBase {

  private double m_speedMult;
  private VictorSPX motor;
  private DigitalInput limitSwitch;

  public SpinnerArm(double speedMultiplier) {
    motor = new VictorSPX(Constants.SPINNER_ARM_MOTOR);
    limitSwitch = new DigitalInput(Constants.SPINNER_ARM_LIMIT_SWITCH);
    m_speedMult = speedMultiplier;
  }
  
  public void move(int speed) {
    motor.set(VictorSPXControlMode.PercentOutput, m_speedMult * (double) speed);
  }
  
  public boolean limitSwitchTriggered() {
    return limitSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}