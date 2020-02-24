package com.north6960.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.north6960.controlpanel.Spinner;

public class MoveToFMSColor extends CommandBase {

  private Spinner _spinner;

  public MoveToFMSColor(Spinner spinner) {
    // Use addRequirements() here to declare subsystem dependencies.
    _spinner = spinner;
    addRequirements(spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _spinner.moveToFMSColor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _spinner.wheel.move(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return _spinner.isColorMatched();
  }
}
