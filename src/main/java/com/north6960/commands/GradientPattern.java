package com.north6960.commands;

import com.north6960.subsystems.lights.RGB;

import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class GradientPattern extends CommandBase {

  private final RGB m_rgb;
  private final int m_length;
  private Color8Bit[] m_colors;

  public GradientPattern(RGB rgb, int length, Color8Bit... colors) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(rgb);
    m_rgb = rgb;
    m_length = length;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_rgb.strip.setGradient(m_length, m_colors);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_rgb.strip.shiftPatternUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(interrupted) {
      m_rgb.strip.clear();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
