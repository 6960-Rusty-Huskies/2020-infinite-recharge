package com.north6960.powercells;

import com.north6960.drive.DriveBase;
import com.north6960.vision.Limelight;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootPowerCells extends SequentialCommandGroup {

  /**
   * Creates a new ShootPowerCells.
   */
  public ShootPowerCells(DriveBase driveBase, PowerCellManagement pcm, int numToShoot) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new PIDCommand(
      new PIDController(0., 0., 0.), 
      () -> Limelight.getOffsetX(), 
      0, 
      output -> { driveBase.arcadeDrive(output, 0); }, 
      driveBase),

    new PIDCommand(
      new PIDController(0, 0, 0), 
      () -> driveBase.getImuCompassHeading(), 
      driveBase.getImuCompassHeading(), 
      output -> { driveBase.arcadeDrive(output, 0); }, 
      driveBase)
    .alongWith( 
      new InstantCommand(() -> pcm.setUpShooter() ))
    .alongWith(
      new ShootCell(pcm, numToShoot) ));
  }
}
