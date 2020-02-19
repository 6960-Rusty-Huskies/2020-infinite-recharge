/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * The "holding chamber" for power cells waiting to be shot.
 */
public class Index extends SubsystemBase {

  private IndexSection entrance, exit;
  public int powerCellCount;

  public Index() {
    entrance = new IndexSection(Constants.INDEX_LOWER_BEAM_BREAK, Constants.INDEX_LOWER_MOTOR);
    exit = new IndexSection(Constants.INDEX_UPPER_BEAM_BREAK, Constants.INDEX_UPPER_MOTOR);
  }

  public void driveUpper(double speed) {
    exit.moveMotor(speed);
  }

  public void driveLower(double speed) {
    entrance.moveMotor(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(entrance.ballPassed()) powerCellCount++;
    if(exit.ballPassed()) powerCellCount--;
  }
}
