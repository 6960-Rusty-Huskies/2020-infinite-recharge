package com.north6960.powercells;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants;

/**
 * The "holding chamber" for power cells waiting to be shot.
 */
public class Index extends SubsystemBase {

  private IndexSection lower, upper;
  private int powerCellCount;

  public Index() {
    lower = new IndexSection(Constants.INDEX_LOWER_BEAM_BREAK, Constants.INDEX_LOWER_MOTOR);
    upper = new IndexSection(Constants.INDEX_UPPER_BEAM_BREAK, Constants.INDEX_UPPER_MOTOR);
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
    if (lower.ballPassed(true)) {
      powerCellCount++;
    }
    if (upper.ballPassed(false)) {
      powerCellCount--;
    }
  }
}
