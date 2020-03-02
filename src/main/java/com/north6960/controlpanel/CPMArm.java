package com.north6960.controlpanel;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.north6960.Constants.CAN;
import com.north6960.Constants.Digital;
import com.north6960.controller.DriverController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CPMArm extends SubsystemBase {

  private VictorSPX motor;
  private DigitalInput limitSwitch;
  private DriverController controller;

  public CPMArm() {
    motor = new VictorSPX(CAN.CPM_ARM_MOTOR);
    limitSwitch = new DigitalInput(Digital.SPINNER_ARM_LIMIT_SWITCH);
  }
  
  public void set(double speed) {
    motor.set(VictorSPXControlMode.PercentOutput, speed);
  }
  
  public boolean limitSwitchTriggered() {
    return limitSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // double speed = 0.5 * controller.getY(Hand.kLeft);
    // if(Math.abs(speed) < 0.1) speed = 0;

    // motor.set(VictorSPXControlMode.PercentOutput, speed);
    SmartDashboard.putBoolean("Spinner arm limit switch", limitSwitchTriggered());
  }
}
