package com.north6960.generatorswitch;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants.CAN;

/**
 * Used to level the robot to help balance on the Generator Switch.
 */
public class Winch extends SubsystemBase {

  private VictorSPX motor;

  public Winch() {
    motor = new VictorSPX(CAN.WINCH_MOTOR);
    motor.setNeutralMode(NeutralMode.Brake);
    motor.setInverted(true);
  }

  public void move(double speed) {
    motor.set(VictorSPXControlMode.PercentOutput, 0.5 * speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
