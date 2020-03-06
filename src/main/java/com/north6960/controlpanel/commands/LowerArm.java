package com.north6960.controlpanel.commands;

import com.north6960.controlpanel.CPM;
import com.north6960.controlpanel.CPMArm;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class LowerArm extends CommandBase {

  private CPMArm cpmArm;

  /**
   * Creates a new LowerArm.
   */
  public LowerArm(CPM cpm) {
    // Use addRequirements() here to declare subsystem dependencies.
    cpmArm = cpm.arm;
    addRequirements(cpm.arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Move the arm down
    cpmArm.set(1.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cpmArm.set(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return cpmArm.limitSwitchTriggered();
  }
}
