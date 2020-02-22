package com.north6960.powercells;

import com.north6960.vision.Limelight;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(index.getPowerCellCount() == 5) {
      intake.setArmUp();
    }
  }
}
