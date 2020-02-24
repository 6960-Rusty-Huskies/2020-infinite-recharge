/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.north6960.commands;

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
