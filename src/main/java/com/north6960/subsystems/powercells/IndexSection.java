package com.north6960.subsystems.powercells;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * One section of the Index.
 */
public class IndexSection extends SubsystemBase {
  
  private BeamBreak beamBreak;
  private CANSparkMax motor;
  private boolean wasTriggeredLastCheck;

  public IndexSection(int beamBreakId, int motorId) {
    beamBreak = new BeamBreak(beamBreakId);
    beamBreak.set(false);
    motor = new CANSparkMax(motorId, MotorType.kBrushless);
  }

  public void drive(double speed) {
    motor.set(speed);
  }

  public boolean ballPassed() {
    return !beamBreak.isTriggered() && wasTriggeredLastCheck;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    wasTriggeredLastCheck = beamBreak.isTriggered();
  }
}
