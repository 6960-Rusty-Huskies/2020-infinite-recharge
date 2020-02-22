package com.north6960.subsystems.powercells;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.north6960.Constants.Analog;
import com.north6960.Constants.CAN;

/**
 * The "holding chamber" for power cells waiting to be shot.
 */
public class Index extends SubsystemBase {
  private IndexSection entrance, exit;
  private int powerCellCount;
  private boolean manualControl;

  public Index() {
    entrance = new IndexSection(Analog.INDEX_LOWER_BEAM_BREAK, CAN.INDEX_LOWER_MOTOR);
    exit = new IndexSection(Analog.INDEX_UPPER_BEAM_BREAK, CAN.INDEX_UPPER_MOTOR);
  }

  public int getPowerCellCount() {
    return powerCellCount;
  }

  public void driveUpper(double speed) {
    exit.drive(speed);
  }

  public void driveLower(double speed) {
    entrance.drive(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (entrance.ballPassed())
      powerCellCount++;
    if (exit.ballPassed())
      powerCellCount--;
  }
}
