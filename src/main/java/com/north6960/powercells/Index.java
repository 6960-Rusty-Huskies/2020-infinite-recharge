package com.north6960.powercells;

import com.north6960.Constants.Analog;
import com.north6960.Constants.CAN;
import com.north6960.Constants.PID;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The "holding chamber" for power cells waiting to be shot.
 */
public class Index extends SubsystemBase {
  private IndexSection lower, upper;
  private int powerCellCount;

  public Index() {
    lower = new IndexSection(Analog.INDEX_LOWER_BEAM_BREAK, CAN.INDEX_LOWER_MOTOR, PID.INDEX_LOWER_P, PID.INDEX_LOWER_FF);
    upper = new IndexSection(Analog.INDEX_UPPER_BEAM_BREAK, CAN.INDEX_UPPER_MOTOR, PID.INDEX_UPPER_P, PID.INDEX_UPPER_FF);
  }

  /**
   * @return Whether the upper beam bread is currently triggered.
   */
  public boolean getUpperBB() {
    return upper.getBeamBreak();
  }

  /**
   * 
   * @param fullPass Whether to consider "passed" when the 
   * @return
   */
  public boolean getUpperPCPassed(boolean fullPass) {
    return upper.ballPassed(fullPass);
  }

  public boolean getLowerBB() {
    return lower.getBeamBreak();
  }

  public boolean getLowerPCPassed(boolean fullPass) {
    return lower.ballPassed(fullPass);
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
