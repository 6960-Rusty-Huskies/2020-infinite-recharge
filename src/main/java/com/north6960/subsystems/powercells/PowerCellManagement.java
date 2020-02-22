package com.north6960.subsystems.powercells;

import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PowerCellManagement extends SubsystemBase {

  private Index m_index;
  private Intake m_intake;
  private Shooter m_shooter;
  private Hood m_hood;

  /**
   * Creates a new PowerCellManagement.
   */
  public PowerCellManagement() {
    m_index = new Index();
    m_intake = new Intake();
    m_shooter = new Shooter();
    m_hood = new Hood();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(m_index.getPowerCellCount() == 5) {
      // TODO raise intake
    }

    

  }
}
