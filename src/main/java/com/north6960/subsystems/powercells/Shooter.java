package com.north6960.subsystems.powercells;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The set of motors that project Power Cells into the goal.
 */
public class Shooter extends SubsystemBase {

  public Chamber chamber;
  public Hood hood;
  private boolean isManual;
  
  public Shooter() {
    chamber = new Chamber();
    hood = new Hood();

    hood.enable();
  }

  public void setManual(boolean setManual) {
    if(setManual != isManual) {
      if(isManual) {
        hood.enable();
      }

      else hood.disable();
    }

    isManual = setManual;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(!isManual) {
      hood.setMotor(0); // TO DO: Connect Operator control
    }
  }
}
