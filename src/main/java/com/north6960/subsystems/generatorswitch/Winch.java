package com.north6960.subsystems.generatorswitch;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.north6960.Constants.CAN;
import com.north6960.utils.Direction;

/**
 * Used to level the robot to help balance on the Generator Switch.
 */
public class Winch extends SubsystemBase {

  private VictorSPX motor;
  private double spd;

  public Winch(double speed) {
    motor = new VictorSPX(CAN.WINCH_MOTOR);
    spd = speed;
  }

  public void move(Direction direction) {
    double speed = 0.0;

    switch(direction) {
      case up:
        speed = spd;
        break;
      case down:
        speed = -spd;
        break;
      case stopped:
        speed = 0.0;
        break;
      case left:
      case right:
        break;
    }

    motor.set(VictorSPXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
