package com.north6960.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.north6960.controlpanel.Spinner;

public class MoveToColor extends CommandBase {

  private Spinner _spinner;

  public MoveToColor(Spinner spinner) {
    // Use addRequirements() here to declare subsystem dependencies.
    _spinner = spinner;
    addRequirements(spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _spinner.arm.set(-1.0);
    _spinner.performPositionControl();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(_spinner.arm.limitSwitchTriggered()) {
      _spinner.arm.set(0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    new WaitCommand(0.25);
    _spinner.wheel.move(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return _spinner.isColorMatched();
  }
}
