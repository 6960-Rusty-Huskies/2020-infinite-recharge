package com.north6960.controlpanel.commands;

import com.north6960.controlpanel.ControlPanelManipulator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class PositionControl extends SequentialCommandGroup {

  private ControlPanelManipulator cpm;

  public PositionControl(ControlPanelManipulator cpm) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new LowerArm(cpm),
      new MoveToFMSColor(cpm),
      new WaitCommand(3),
      new RotateControlPanel(cpm, 2),
      new RaiseArm(cpm)
    );
    this.cpm = cpm;
  }

  @Override
  public void end(boolean interrupted) {
    if(interrupted) {
      new RaiseArm(cpm).schedule();
    }
  }
}
