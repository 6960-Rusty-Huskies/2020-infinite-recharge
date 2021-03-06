package com.north6960.controlpanel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.north6960.controlpanel.ControlPanelManipulator;

public class MoveToFMSColor extends CommandBase {

  private ControlPanelManipulator cpm;

  public MoveToFMSColor(ControlPanelManipulator cpm) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.cpm = cpm;
    addRequirements(cpm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cpm.moveTowardFMSColor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cpm.moveWheel(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return cpm.isColorMatched();
  }
}
