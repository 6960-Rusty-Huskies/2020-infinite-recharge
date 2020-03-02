package com.north6960.powercells;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * One section of the Index.
 */
public class IndexSection extends SubsystemBase {
  
  public BeamBreak beamBreak;
  private CANSparkMax motor;
  private CANPIDController pidController;
  public boolean wasTriggeredLastCheck;
  private double rpm;

  public IndexSection(int beamBreakId, int motorId, double p, double ff, boolean inverted) {
    beamBreak = new BeamBreak(beamBreakId);

    motor = new CANSparkMax(motorId, MotorType.kBrushless);
    motor.setInverted(inverted);

    pidController = motor.getPIDController();
    pidController.setP(p);
    pidController.setFF(ff);
  }

  public boolean getBeamBreak() {
    return beamBreak.isTriggered();
  }

  public void drive(double speed) {
    rpm = speed;
  }

  public double getRPM() {
    return motor.getEncoder().getVelocity();
  }

  public double getVoltage() {
    return motor.getBusVoltage();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    wasTriggeredLastCheck = beamBreak.isTriggered();

    motor.getPIDController().setReference(rpm, ControlType.kVelocity);
  }
}
