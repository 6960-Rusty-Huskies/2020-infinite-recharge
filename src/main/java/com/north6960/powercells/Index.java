package com.north6960.powercells;

import com.north6960.Constants.Analog;
import com.north6960.Constants.CAN;
import com.north6960.controller.DriverController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The "holding chamber" for power cells waiting to be shot.
 */
public class Index extends SubsystemBase {
  private IndexSection lower, upper;
  private int powerCellCount;

  DriverController controller = new DriverController();

  public Index() {
    lower = new IndexSection(Analog.INDEX_LOWER_BEAM_BREAK, CAN.INDEX_LOWER_MOTOR);
    upper = new IndexSection(Analog.INDEX_UPPER_BEAM_BREAK, CAN.INDEX_UPPER_MOTOR);
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
    if (lower.ballPassed(false)) {
      powerCellCount++;
    }
    if (upper.ballPassed(true)) {
      powerCellCount--;
    }
  }
}
