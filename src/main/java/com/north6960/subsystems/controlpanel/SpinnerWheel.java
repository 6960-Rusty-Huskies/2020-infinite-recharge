package com.north6960.subsystems.controlpanel;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.north6960.Constants;
import com.north6960.utils.Direction;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpinnerWheel extends SubsystemBase {
  
  private VictorSPX motor;
  private double speed;

  public SpinnerWheel() {
    motor = new VictorSPX(Constants.SPINNER_WHEEL_MOTOR);
  }

  public void move(Direction direction) {
    switch (direction) {
      case left:
        motor.set(VictorSPXControlMode.PercentOutput, -speed);
        break;
      case right:
        motor.set(VictorSPXControlMode.PercentOutput, speed);
        break;
      case stopped:
        motor.set(VictorSPXControlMode.PercentOutput, 0.0);
      case up:
      case down:
        return;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
