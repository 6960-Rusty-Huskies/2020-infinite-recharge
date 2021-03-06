package com.north6960.controlpanel.commands;

import com.north6960.controlpanel.ControlPanelManipulator;
import com.north6960.controlpanel.CPMArm;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class LowerArm extends CommandBase {

  private ControlPanelManipulator cpm;

  /**
   * Creates a new LowerArm.
   */
  public LowerArm(ControlPanelManipulator cpm) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.cpm = cpm;
    addRequirements(cpm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Move the arm down
    cpm.moveArm(1.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cpm.moveArm(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return cpm.lowerLimitSwitchTriggered();
  }
}
