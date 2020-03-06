package com.north6960.powercells;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * One section of the Index.
 */
public class IndexSection extends SubsystemBase {
  
  private BeamBreak beamBreak;
  private CANSparkMax motor;
  public boolean wasTriggeredLastCheck;

  public IndexSection(int beamBreakId, int motorId, boolean inverted) {
    beamBreak = new BeamBreak(beamBreakId);

    motor = new CANSparkMax(motorId, MotorType.kBrushless);
    motor.setInverted(inverted);
  }

  public boolean getBeamBreak() {
    return beamBreak.get();
  }

  public void drive(double speed) {
    motor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    wasTriggeredLastCheck = beamBreak.get();
  }
}
