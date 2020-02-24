package com.north6960.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class LowerArm extends CommandBase {

  SpinnerArm spinnerArm;

  /**
   * Creates a new LowerArm.
   */
  public LowerArm(Spinner spinner) {
    // Use addRequirements() here to declare subsystem dependencies.
    spinnerArm = spinner.arm;
    addRequirements(spinner.arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Move the arm down
    spinnerArm.set(1.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    spinnerArm.set(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return spinnerArm.limitSwitchTriggered();
  }
}
