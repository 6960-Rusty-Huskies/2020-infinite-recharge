package com.north6960.subsystems.powercells;

import com.north6960.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Chamber extends SubsystemBase {
  
  private double speed;
  private CANSparkMax motor;

  public Chamber() {
    motor = new CANSparkMax(Constants.SHOOTER_MOTOR, MotorType.kBrushless);
  }

  public void setSpeed(double speedToSet) {
    speed = speedToSet;
  }

  public void set(boolean on) {
    motor.set(on ? speed : 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
