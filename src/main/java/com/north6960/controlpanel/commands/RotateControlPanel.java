package com.north6960.controlpanel.commands;

import com.north6960.controlpanel.ColorEnum;
import com.north6960.controlpanel.ControlPanelManipulator;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateControlPanel extends CommandBase {

  private ControlPanelManipulator cpm;
  private int numSectionsPassed = 0;
  private int numToRotate;
  private ColorEnum currentColor;

  /**
   * Creates a new RotationControl.
   */
  public RotateControlPanel(ControlPanelManipulator cpm, int numToRotate) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(cpm);
    this.cpm = cpm;
    this.numToRotate = numToRotate;
    currentColor = cpm.getPredictedFieldColor();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cpm.moveWheel(1.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(cpm.getPredictedFieldColor() != currentColor) {
      numSectionsPassed++;
      currentColor = cpm.getPredictedFieldColor();
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
    return numSectionsPassed >= numToRotate;
  }
}
