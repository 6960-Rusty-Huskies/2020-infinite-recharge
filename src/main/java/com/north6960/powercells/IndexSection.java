package com.north6960.powercells;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/**
 * One section of the Index.
 */
public class IndexSection extends PIDSubsystem {
  
  private BeamBreak beamBreak;
  private CANSparkMax motor;
  private CANEncoder encoder;
  private boolean wasTriggeredLastCheck;

  public IndexSection(int beamBreakId, int motorId) {
    super(new PIDController(0.05, 0, 0), 100);
    beamBreak = new BeamBreak(beamBreakId);
    beamBreak.set(false);

    motor = new CANSparkMax(motorId, MotorType.kBrushless);
    encoder = motor.getEncoder();
  }

  public void drive(double speed) {
    motor.set(speed);
  }

  public boolean ballPassed(boolean fullPass) {
    if(fullPass) {
      return !beamBreak.isTriggered() && wasTriggeredLastCheck;
    }
    
    else {
      return beamBreak.isTriggered() && !wasTriggeredLastCheck;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    wasTriggeredLastCheck = beamBreak.isTriggered();
  }

  @Override
  public double getMeasurement() {
    return motor.getEncoder().getVelocity();
  }

  @Override
  public void useOutput(double output, double setpoint) {
    motor.set(output);
  }
}
