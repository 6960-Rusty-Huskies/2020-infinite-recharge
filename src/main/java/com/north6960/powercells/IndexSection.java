package com.north6960.powercells;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * One section of the Index.
 */
public class IndexSection extends SubsystemBase {
  
  private BeamBreak beamBreak;
  private CANSparkMax motor;
  private CANPIDController pidController;
  private boolean wasTriggeredLastCheck;
  private double rpm;

  public IndexSection(int beamBreakId, int motorId, double p, double ff) {
    beamBreak = new BeamBreak(beamBreakId);

    motor = new CANSparkMax(motorId, MotorType.kBrushless);

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

    motor.getPIDController().setReference(rpm, ControlType.kVelocity);
  }
}
