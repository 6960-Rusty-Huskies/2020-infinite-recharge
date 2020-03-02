package com.north6960.generatorswitch;

import com.north6960.controller.DriverController;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The Climber elevates the robot in the Endgame to attach it to their switch.
 */
public class Climber extends SubsystemBase {

  public Lift lift;
  public Winch winch;
  private DriverController controller = new DriverController();

  public Climber() {
    lift = new Lift();
    winch = new Winch();
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
