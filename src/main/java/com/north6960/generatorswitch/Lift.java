package com.north6960.generatorswitch;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.north6960.Constants.CAN;
import com.north6960.Constants.Digital;

/**
 * The arm that extends and elevates the robot onto the Generator Switch.
 */
public class Lift extends SubsystemBase {
  private VictorSPX motor;
  private DigitalInput limitSwitch;
  private double spd;

  public Lift(double speed) {
    motor = new VictorSPX(CAN.LIFT_MOTOR);
    limitSwitch = new DigitalInput(Digital.LIFT_SWITCH);
    spd = speed;
  }

  /**
   * Move the lift. The speed is always between -1 and 1. Negative moves the lift down, positive moves it up.
   */
  public void move(double speed) {
    if(limitSwitch.get()) {
      speed = 0.0;
    }
    
    motor.set(VictorSPXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
