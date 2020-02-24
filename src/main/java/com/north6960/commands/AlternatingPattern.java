/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.north6960.commands;

import com.north6960.lights.RGB;

import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AlternatingPattern extends CommandBase {

  private RGB m_rgb;
  private final int m_width;
  private Color8Bit[] m_colors;

  /**
   * Creates a new AlternatingPattern.
   */
  public AlternatingPattern(RGB rgb, int width, Color8Bit... colors) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(rgb);
    m_rgb = rgb;
    m_width = width;
    m_colors = colors;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_rgb.strip.setAlternating(m_width, m_colors);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // m_rgb.strip.shiftPatternUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
