package com.north6960.generatorswitch;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants.CAN;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Used to level the robot to help balance on the Generator Switch.
 */
public class Winch extends SubsystemBase {

  private CANSparkMax motor;

  public Winch() {
    motor = new CANSparkMax(CAN.WINCH_MOTOR, MotorType.kBrushless);
    motor.setIdleMode(IdleMode.kBrake);
  }

  public void set(double speed) {
    motor.set(0.5 * speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
