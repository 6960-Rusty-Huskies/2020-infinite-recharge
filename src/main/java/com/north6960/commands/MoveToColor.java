package com.north6960.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.north6960.subsystems.controlpanel.Spinner;
import com.north6960.utils.Direction;

public class MoveToColor extends CommandBase {

  private Spinner m_spinner;

  public MoveToColor(Spinner spinner) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_spinner = spinner;
    addRequirements(spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_spinner.arm.move(Direction.down);
    m_spinner.moveToFMSColor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_spinner.arm.limitSwitchTriggered()) {
      m_spinner.arm.move(Direction.stopped);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    new WaitCommand(0.25);
    m_spinner.wheel.move(Direction.stopped);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_spinner.isColorMatched();
  }
}
