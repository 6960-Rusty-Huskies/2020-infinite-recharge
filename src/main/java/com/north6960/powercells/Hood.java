package com.north6960.powercells;

import com.north6960.Constants;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Hood extends PIDSubsystem {

  private CANSparkMax motor;
  private CANEncoder encoder;

  public Hood() {
    super(new PIDController(0, 0, 0));

    motor = new CANSparkMax(Constants.CAN.HOOD_MOTOR, MotorType.kBrushless);

    encoder = motor.getEncoder();
    encoder.setPositionConversionFactor(1. / 360.);
  }

  public void setMotor(double speed) {
    motor.set(speed);
  }

  public void setAngle(double angle) {
    setSetpoint(angle);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    motor.set(output);
  }

  @Override
  public double getMeasurement() {
    return encoder.getPosition();
  }
}
