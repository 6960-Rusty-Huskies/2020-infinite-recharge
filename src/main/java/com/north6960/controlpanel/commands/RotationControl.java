package com.north6960.controlpanel.commands;

import com.north6960.controlpanel.ControlPanelManipulator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RotationControl extends SequentialCommandGroup {
  /**
   * Creates a new RotationControl.
   */
  public RotationControl(ControlPanelManipulator cpm) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new LowerArm(cpm),
      new RotateControlPanel(cpm, 26),
      new RaiseArm(cpm)
    );
  }
}
