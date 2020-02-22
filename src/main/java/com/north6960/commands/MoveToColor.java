package com.north6960.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.north6960.subsystems.controlpanel.Spinner;
import com.north6960.utils.Direction;

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
    _spinner.arm.move(Direction.down);
    _spinner.moveToFMSColor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(_spinner.arm.limitSwitchTriggered()) {
      _spinner.arm.move(Direction.stopped);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    new WaitCommand(0.25);
    _spinner.wheel.move(Direction.stopped);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return _spinner.isColorMatched();
  }
}