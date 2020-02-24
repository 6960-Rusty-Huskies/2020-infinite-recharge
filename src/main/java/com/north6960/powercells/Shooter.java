package com.north6960.powercells;

import com.north6960.Constants;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The set of motors that project Power Cells into the goal.
 */
public class Shooter extends SubsystemBase {

  private CANSparkMax motor;
  private CANEncoder encoder;

  private boolean isManual;
  
  public Shooter() {
    motor = new CANSparkMax(Constants.CAN.SHOOTER_MOTOR, MotorType.kBrushless);
    encoder = motor.getEncoder();
  }

  public void setManual(boolean setManual) {

    isManual = setManual;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
