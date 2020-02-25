package com.north6960.powercells;

import com.north6960.Constants.CAN;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Shooter extends PIDSubsystem {

  private CANSparkMax spinnerMotor;
  private CANEncoder encoder;

  public Shooter() {
    super(new PIDController(0.0003, 0.0000075, 0.), 1000.);
    spinnerMotor = new CANSparkMax(CAN.SHOOTER_MOTOR, MotorType.kBrushless);
    encoder = spinnerMotor.getEncoder(EncoderType.kHallSensor, 42);
    spinnerMotor.setInverted(true);
    
    SmartDashboard.putNumber("CPR", encoder.getCountsPerRevolution());
  }

  public void setSpeed(double speed) {
    setSetpoint(speed);
  }

  @Override
  public double getMeasurement() {
    return encoder.getVelocity();
  }

  @Override
  public void useOutput(double output, double setpoint) {
    spinnerMotor.set(output);
  }
}
