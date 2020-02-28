package com.north6960.controlpanel;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.north6960.Constants.CAN;
import com.north6960.Constants.Digital;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CPMArm extends SubsystemBase {

  private VictorSPX motor;
  private DigitalInput limitSwitch;

  public CPMArm() {
    motor = new VictorSPX(CAN.CPM_ARM_MOTOR);
    limitSwitch = new DigitalInput(Digital.SPINNER_ARM_SWITCH);
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
  }
}
