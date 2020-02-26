package com.north6960.lights;

import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShiftPattern extends InstantCommand {
  public ShiftPattern(RGB rgb) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(rgb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }
}
