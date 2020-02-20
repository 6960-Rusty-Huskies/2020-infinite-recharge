/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.BeamBreak;

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
