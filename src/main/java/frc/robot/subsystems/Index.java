/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Index extends SubsystemBase {

  public IndexSection lowerSection, upperSection;
  public int powerCellCount;

  /**
   * Creates a new Index.
   */
  public Index() {
    lowerSection = new IndexSection(Constants.INDEX_LOWER_BEAM_BREAK, Constants.INDEX_LOWER_MOTOR);
    upperSection = new IndexSection(Constants.INDEX_UPPER_BEAM_BREAK, Constants.INDEX_UPPER_MOTOR);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
