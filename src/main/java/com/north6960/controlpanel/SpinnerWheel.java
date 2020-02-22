package com.north6960.controlpanel;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.north6960.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpinnerWheel extends SubsystemBase {
  
  private VictorSPX motor;
  private double m_speedMult;

  public SpinnerWheel(double speedMultiplier) {
    motor = new VictorSPX(Constants.SPINNER_WHEEL_MOTOR);
    m_speedMult = speedMultiplier;
  }

  public void move(int direction) {
    motor.set(VictorSPXControlMode.PercentOutput, m_speedMult * (double) direction);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
