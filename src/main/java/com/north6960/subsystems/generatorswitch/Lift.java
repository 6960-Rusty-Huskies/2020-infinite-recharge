package com.north6960.subsystems.generatorswitch;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants;

/**
 * The arm that extends and elevates the robot onto the Generator Switch.
 */
public class Lift extends SubsystemBase {
  private VictorSPX motor;
  private DigitalInput limitSwitch;

  public Lift() {
    motor = new VictorSPX(Constants.LIFT_MOTOR);
    limitSwitch = new DigitalInput(Constants.LIFT_SWITCH);
  }

  /**
   * Move the lift. The speed is always between -1 and 1. Negative moves the lift down, positive moves it up.
   * @param speed the speed with which to move the lift.
   */
  public void move(double speed) {
    if(!limitSwitch.get()) {
      motor.set(VictorSPXControlMode.Velocity, speed);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}