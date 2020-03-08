package com.north6960.autonomous;

import com.north6960.drive.DriveBase;
import com.north6960.powercells.PowerCellManagement;
import com.north6960.powercells.ShootingType;
import com.north6960.powercells.commands.ShootPowerCells;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

public class DefaultAutoCommand extends SequentialCommandGroup {
  /**
   * Creates a new DefaultAutoCommand.
   */
  public DefaultAutoCommand(DriveBase driveBase, PowerCellManagement pcm, ShootPowerCells command) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new InstantCommand( () -> pcm.intake.armMotor.set(0.5), pcm.intake),
      new WaitUntilCommand(() -> !pcm.intake.limitSwitchTriggered()).withTimeout(0.5),
      new InstantCommand( () -> pcm.intake.armMotor.set(0), pcm.intake), 
      command.withTimeout(8),
      new InstantCommand( () -> driveBase.arcadeDrive(-0.75, 0), driveBase ),
      new RunCommand(() -> driveBase.arcadeDrive(-0.75, 0), driveBase).withTimeout(1.5),
      new InstantCommand( () -> driveBase.arcadeDrive(0, 0), driveBase)
    );
  }
}
