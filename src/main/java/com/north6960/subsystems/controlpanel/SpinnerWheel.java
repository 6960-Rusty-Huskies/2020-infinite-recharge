package com.north6960.subsystems.controlpanel;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.north6960.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpinnerWheel extends SubsystemBase {
  
  private VictorSPX motor;

  public SpinnerWheel() {
    motor = new VictorSPX(Constants.SPINNER_WHEEL_MOTOR);
  }

  public void move(double speed) {
    motor.set(VictorSPXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
