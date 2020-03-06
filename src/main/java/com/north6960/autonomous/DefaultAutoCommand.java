package com.north6960.autonomous;

import com.north6960.drive.DriveBase;
import com.north6960.powercells.PowerCellManagement;
import com.north6960.powercells.ShootingType;
import com.north6960.powercells.commands.ShootPowerCells;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class DefaultAutoCommand extends SequentialCommandGroup {
  /**
   * Creates a new DefaultAutoCommand.
   */
  public DefaultAutoCommand(DriveBase driveBase, PowerCellManagement pcm) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new InstantCommand( () -> driveBase.arcadeDrive(-1.0, 0) ),
      new WaitCommand(0.5),
      new InstantCommand( () -> driveBase.arcadeDrive(0, 0) ),
      new ShootPowerCells(driveBase, pcm, ShootingType.auto));
  }
}
