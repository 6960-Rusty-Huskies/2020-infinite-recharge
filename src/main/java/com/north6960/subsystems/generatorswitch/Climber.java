package com.north6960.subsystems.generatorswitch;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The Climber elevates the robot in the Endgame to attach it to their switch.
 */
public class Climber extends SubsystemBase {

  private Lift lift;
  private Winch winch;

  public Climber() {
    lift = new Lift();
    winch = new Winch();
  }

  public void moveLift(double speed) {
    lift.move(speed);
  }

  public void moveWinch(double speed) {
    winch.move(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
