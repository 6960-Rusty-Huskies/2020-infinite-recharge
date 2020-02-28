package com.north6960.powercells;

import com.north6960.Constants.CAN;
import com.north6960.Constants.PID;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Hood extends PIDSubsystem {

  private CANSparkMax motor;
  private CANEncoder encoder;
  private ArmFeedforward feedforward;

  public Hood() {
    super(
        // The PIDController used by the subsystem
        new PIDController(PID.HOOD_P, 0, PID.HOOD_D));

    feedforward = new ArmFeedforward(PID.HOOD_S, PID.HOOD_COS, PID.HOOD_V, PID.HOOD_A);
    motor = new CANSparkMax(CAN.HOOD_MOTOR, MotorType.kBrushless);
    encoder = motor.getEncoder();

    // Encoder should give an output in radians.
    encoder.setPositionConversionFactor(2. * Math.PI);
  }

  public void setMotor(double speed) {
    motor.set(speed);
  }

  public void setAngle(double angle) {
    setSetpoint(angle);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    // Velocity in feedforward.calculate() is in radians per second.
    motor.setVoltage(output + feedforward.calculate(setpoint, output));
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return encoder.getPosition();
  }
}
