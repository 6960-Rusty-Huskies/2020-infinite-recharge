package com.north6960.powercells;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PowerCellManagement extends SubsystemBase {

  private Index index;
  private Intake intake;
  private Shooter shooter;
  private Hood hood;

  /**
   * Creates a new PowerCellManagement.
   */
  public PowerCellManagement() {
    index = new Index();
    intake = new Intake();
    shooter = new Shooter();
    hood = new Hood();
  }

  public int getBallCount() {
    return index.getPowerCellCount();
  }

  public void intakeSetup() {
    if(!intake.isDown()) {
      intake.setDown();
    }
  }
    
  @Override
  public void periodic() {

  }
}
