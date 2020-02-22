package com.north6960.subsystems.powercells;

import com.north6960.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/**
 * The set of motors that project Power Cells into the goal.
 */
public class Shooter extends PIDSubsystem {
  private boolean isManual;
  private CANSparkMax spinnerMotor;

  public Shooter() {
    super(new PIDController(0., 0., 0.));

    spinnerMotor = new CANSparkMax(Constants.SHOOTER_MOTOR, MotorType.kBrushless);
  }

  public void setSpeed(double speed) {
    setSetpoint(speed);
  }

  @Override
  public double getMeasurement() {
    return spinnerMotor.get();
  }

  @Override
  public void useOutput(double output, double setpoint) {
    spinnerMotor.set(output);
  }
}
