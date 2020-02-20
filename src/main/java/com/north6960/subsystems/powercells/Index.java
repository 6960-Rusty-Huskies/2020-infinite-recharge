package com.north6960.subsystems.powercells;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants;

/**
 * The "holding chamber" for power cells waiting to be shot.
 */
public class Index extends SubsystemBase {

  private IndexSection lowerSection, upperSection;
  public int powerCellCount;

  public Index() {
    lowerSection = new IndexSection(Constants.INDEX_LOWER_BEAM_BREAK, Constants.INDEX_LOWER_MOTOR);
    upperSection = new IndexSection(Constants.INDEX_UPPER_BEAM_BREAK, Constants.INDEX_UPPER_MOTOR);
  }

  public void driveUpper(double speed) {
    upperSection.moveMotor(speed);
  }

  public void driveLower(double speed) {
    lowerSection.moveMotor(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
