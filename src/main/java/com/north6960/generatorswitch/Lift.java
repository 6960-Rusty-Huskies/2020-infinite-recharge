package com.north6960.generatorswitch;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants.CAN;
import com.north6960.Constants.Digital;

/**
 * The arm that extends and elevates the robot onto the Generator Switch.
 */
public class Lift extends SubsystemBase {
  private WPI_TalonSRX motor;
  private DigitalInput limitSwitch;

  public Lift() {
    motor = new WPI_TalonSRX(CAN.LIFT_MOTOR);
    motor.setInverted(true);
    limitSwitch = new DigitalInput(Digital.LIFT_SWITCH);
  }

  /**
   * Move the lift. The speed is always between -1 and 1. Negative moves the lift down, positive moves it up.
   * @param speed the speed with which to move the lift.
   */
  public void move(double speed) {
    // if(!limitSwitch.get()) {
      motor.set(speed);
    // }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
