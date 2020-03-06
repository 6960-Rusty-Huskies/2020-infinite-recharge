package com.north6960.powercells.commands;

import com.north6960.Constants.Physical;
import com.north6960.drive.DriveBase;
import com.north6960.drive.commands.FollowLimelightOffsetX;
import com.north6960.powercells.PowerCellManagement;
import com.north6960.powercells.ShootingType;
import com.north6960.vision.LedMode;
import com.north6960.vision.Limelight;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

public class ShootPowerCells extends SequentialCommandGroup {

  private PowerCellManagement pcm;

  /**
   * Creates a new ShootPowerCells.
   */
  public ShootPowerCells(DriveBase driveBase, PowerCellManagement pcm, ShootingType type) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      // new FollowLimelightOffsetX(driveBase, pcm)
      // .alongWith(new SequentialCommandGroup(
        new InstantCommand( () -> pcm.intake.putUp()),
        new InstantCommand( () -> pcm.index.driveUpper(-Physical.INDEX_SPEED)),
        new WaitCommand(0.25),
        new InstantCommand( () -> pcm.index.driveUpper(0) ),
        new InstantCommand(() -> pcm.setUpShooter(type)),
        new WaitUntilCommand(pcm.shooter::atSetpoint),
        new PerpetualCommand(new InstantCommand( () -> { 
          pcm.index.driveLower(Physical.INDEX_SPEED);
          pcm.index.driveUpper(Physical.INDEX_SPEED);
        }, pcm))  
      // ))
      );

      this.pcm = pcm;
  }

  @Override
  public void initialize() {
    super.initialize();
    Limelight.setLed(LedMode.on);
  }

  @Override
  public void end(boolean interrupted) {
    pcm.index.driveLower(0);
    pcm.index.driveUpper(0);
    pcm.shooter.setSpeed(0);
    pcm.intake.putDown();
    Limelight.setLed(LedMode.off);
  }
}
