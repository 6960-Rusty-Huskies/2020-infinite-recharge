package com.north6960.powercells;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants;

/**
 * The set of motors that project Power Cells into the goal.
 */
public class Shooter extends SubsystemBase {

  private double speed;
  private CANSparkMax motor;

  public Shooter() {
    motor = new CANSparkMax(Constants.SHOOTER_MOTOR, MotorType.kBrushless);
  }

  public void setSpeed(double speedToSet) {
    speed = speedToSet;
  }

  public void set(boolean on) {
    if(on) {
      motor.set(speed);
    }

    else motor.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
