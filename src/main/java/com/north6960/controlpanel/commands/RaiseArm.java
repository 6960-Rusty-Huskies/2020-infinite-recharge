package com.north6960.controlpanel.commands;

import com.north6960.controlpanel.ControlPanelManipulator;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RaiseArm extends CommandBase {
  ControlPanelManipulator cpm;
  /**
   * Creates a new RaiseArm.
   */
  public RaiseArm(ControlPanelManipulator cpm) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(cpm);
    this.cpm = cpm;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cpm.moveArm(-1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return cpm.upperLimitSwitchTriggered();
  }
}
