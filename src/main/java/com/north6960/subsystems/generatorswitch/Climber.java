package com.north6960.subsystems.generatorswitch;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The Climber elevates the robot in the Endgame to attach it to their switch.
 */
public class Climber extends SubsystemBase {

  public Lift lift;
  public Winch winch;

  public Climber(double liftSpeed, double winchSpeed) {
    lift = new Lift(liftSpeed);
    winch = new Winch(winchSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
