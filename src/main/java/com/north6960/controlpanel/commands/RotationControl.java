package com.north6960.controlpanel.commands;

import com.north6960.controlpanel.ControlPanelManipulator;
import com.north6960.powercells.PowerCellManagement;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotationControl extends CommandBase {

  private ControlPanelManipulator cpm;
  private int numSectionsPassed = 0;
  private Color currentColor;

  /**
   * Creates a new RotationControl.
   */
  public RotationControl(ControlPanelManipulator cpm) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(cpm);
    this.cpm = cpm;
    currentColor = cpm.getDetectedColor();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cpm.moveWheel(1.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(cpm.getDetectedColor() != currentColor) {
      numSectionsPassed++;
      currentColor = cpm.getDetectedColor();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cpm.moveWheel(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return numSectionsPassed >= 30;
  }
}
