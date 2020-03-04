package com.north6960.powercells.commands;

import com.north6960.Constants.PID;
import com.north6960.Constants.Physical;
import com.north6960.drive.DriveBase;
import com.north6960.drive.FollowLimelightOffsetX;
import com.north6960.powercells.PowerCellManagement;
import com.north6960.vision.LedMode;
import com.north6960.vision.Limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

public class ShootPowerCells extends SequentialCommandGroup {

  /**
   * Creates a new ShootPowerCells.
   */
  public ShootPowerCells(DriveBase driveBase, PowerCellManagement pcm) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new FollowLimelightOffsetX(driveBase, pcm)
      // .alongWith(new SequentialCommandGroup(
      //   new InstantCommand( () -> { 
      //     pcm.index.driveUpper(-Physical.UPPER_INDEX_SPEED);
      //   }),
        // new WaitCommand(0.5),
        // new InstantCommand(() -> pcm.setUpShooter()),
        // new WaitUntilCommand(pcm.shooter::atSetpoint),
        // new InstantCommand( () -> {
        //   pcm.index.driveUpper(Physical.UPPER_INDEX_SPEED);
        //   pcm.index.driveLower(Physical.LOWER_INDEX_SPEED);
        // })  
      // ))
      );
  }
}
