/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

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
