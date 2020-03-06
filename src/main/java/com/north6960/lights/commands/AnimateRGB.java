package com.north6960.lights.commands;

import com.north6960.lights.RGB;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AnimateRGB extends SequentialCommandGroup {
  
  /**
   * Creates a new RGBPattern.
   * @param hz the number of times to update per second.
   * @param pattern the pattern to use. Command name should end in "Pattern".
   */
  public AnimateRGB(RGB rgb, double hz) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super( new InstantCommand(rgb::shiftPatternUp, rgb) , new WaitCommand(1 / hz));
  }
}
