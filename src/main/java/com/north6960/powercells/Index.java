package com.north6960.powercells;

import com.north6960.Constants.Analog;
import com.north6960.Constants.CAN;
import com.north6960.Constants.PID;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The "holding chamber" for power cells waiting to be shot.
 */
public class Index extends SubsystemBase {
  public IndexSection lower, upper;
  private int powerCellCount;

  public Index() {
    lower = 
      new IndexSection(Analog.INDEX_LOWER_BEAM_BREAK, CAN.INDEX_LOWER_MOTOR, PID.INDEX_LOWER_P, PID.INDEX_LOWER_FF, false);
    upper = 
      new IndexSection(Analog.INDEX_UPPER_BEAM_BREAK, CAN.INDEX_UPPER_MOTOR, PID.INDEX_UPPER_P, PID.INDEX_UPPER_FF, true);

    powerCellCount = 0;
  }


  public int getPowerCellCount() {
    return powerCellCount;
  }

  public void driveUpper(double speed) {
    upper.drive(speed);
  }

  public void driveLower(double speed) {
    lower.drive(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(lower.getBeamBreak() && !lower.wasTriggeredLastCheck) {
      powerCellCount++;
    }

    if(!upper.getBeamBreak() && upper.wasTriggeredLastCheck) {
      powerCellCount--;
    }

  }
}
