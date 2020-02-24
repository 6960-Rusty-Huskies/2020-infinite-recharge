package com.north6960.lights;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class RGBPattern extends SequentialCommandGroup {
  /**
   * Creates a new RGBPattern.
   * @param hz the number of times to update per second.
   * @param pattern the pattern to use. Command name should end in "Pattern".
   */
  public RGBPattern(Command pattern, double hz) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(pattern, new WaitCommand(1 / hz));
  }
}
