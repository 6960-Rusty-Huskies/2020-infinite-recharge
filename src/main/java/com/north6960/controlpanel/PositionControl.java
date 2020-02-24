package com.north6960.controlpanel;

import com.north6960.controlpanel.Spinner;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class PositionControl extends CommandBase {

  private Spinner m_spinner;

  public PositionControl(Spinner spinner) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_spinner = spinner;
    addRequirements(spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_spinner.arm.set(-1.0);
    m_spinner.performPositionControl();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_spinner.arm.limitSwitchTriggered()) {
      m_spinner.arm.set(0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    new WaitCommand(0.25);
    m_spinner.wheel.move(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_spinner.isColorMatched();
  }
}
