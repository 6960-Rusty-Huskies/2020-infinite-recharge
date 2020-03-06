package com.north6960.controlpanel;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.north6960.Constants.CAN;
import com.north6960.controller.DriverController;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CPMWheel extends SubsystemBase {
  
  private VictorSPX motor;
  private DriverController controller;

  public CPMWheel() {
    motor = new VictorSPX(CAN.CPM_WHEEL_MOTOR);
  }

  public void move(double speed) {
    motor.set(VictorSPXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // double speed = 0.5 * controller.getX(Hand.kLeft);
    // if(Math.abs(speed) < 0.1) speed = 0;

    // motor.set(VictorSPXControlMode.PercentOutput, speed);
  }
}
