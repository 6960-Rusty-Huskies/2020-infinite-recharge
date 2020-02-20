package com.north6960.powercells;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * One section of the Index.
 */
public class IndexSection extends SubsystemBase {

  private DigitalOutput beamBreak;
  private CANSparkMax motor;

  public IndexSection(int beamBreakId, int motorId) {
    beamBreak = new DigitalOutput(beamBreakId);
    motor = new CANSparkMax(motorId, MotorType.kBrushless);
  }

  public void moveMotor(double speed) {
    motor.set(speed);
  }

  public boolean beamBreakIsTriggered() {
    return beamBreak.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
