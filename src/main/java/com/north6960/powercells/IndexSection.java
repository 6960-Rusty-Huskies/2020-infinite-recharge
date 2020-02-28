package com.north6960.powercells;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * One section of the Index.
 */
public class IndexSection extends SubsystemBase {
  
  private BeamBreak beamBreak;
  private CANSparkMax motor;
  private CANEncoder encoder;
  private boolean wasTriggeredLastCheck;
  private SimpleMotorFeedforward feedforward;

  public IndexSection(int beamBreakId, int motorId) {

    beamBreak = new BeamBreak(beamBreakId);
    beamBreak.setDefaultState(false);

    motor = new CANSparkMax(motorId, MotorType.kBrushless);
    encoder = motor.getEncoder();
  }

  public void drive(double speed) {
    motor.set(speed);
  }

  /**
   * Checks whether a ball has passed in the last scheduler run. Should be called periodically.
   * @param fullPass whether to check the ball at the first point of contact.
   * @return whether a ball has passed.
   */
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
}
