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

public class IndexSection extends SubsystemBase {

  private DigitalOutput beamBreak;
  private CANSparkMax motor;

  /**
   * Creates a new IndexSection.
   * @param beamBreakId the ID of the beam break on the index section.
   */
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
