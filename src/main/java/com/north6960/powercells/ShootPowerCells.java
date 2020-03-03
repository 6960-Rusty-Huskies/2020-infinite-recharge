package com.north6960.powercells;

import com.north6960.Constants.PID;
import com.north6960.Constants.Physical;
import com.north6960.drive.DriveBase;
import com.north6960.drive.FollowLimelightOffsetX;
import com.north6960.vision.LedMode;
import com.north6960.vision.Limelight;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
      new InstantCommand( () -> Limelight.setLed(LedMode.on) ), 
      new FollowLimelightOffsetX(driveBase));
      // .alongWith(
      //   new InstantCommand( () -> { 
      //     pcm.index.driveUpper(-200);
      //     pcm.index.driveLower(-200); 
      //   }).andThen(
      //   new WaitCommand(0.5))
      //   .andThen(
      //   new InstantCommand(() -> pcm.shooter.setSpeed(2000) ))
      //   .andThen(
      //   new WaitUntilCommand( () -> pcm.shooter.atSetpoint() ))
      //   .andThen(
        // new ShootCell(pcm))
      // );
  }
    
  public static class ShootCell extends CommandBase {
    private PowerCellManagement pcm;

    public ShootCell(PowerCellManagement pcm) {
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(pcm);
      this.pcm = pcm;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      pcm.index.driveLower(200);
      pcm.index.driveUpper(200);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      // if(pcm.index.upper.getBeamBreak()) {
      //   pcm.index.driveLower(0);
      //   pcm.index.driveUpper(Physical.UPPER_INDEX_SPEED);
      // }

      // else {
      //   pcm.index.driveUpper(0);
      //   pcm.index.driveLower(Physical.LOWER_INDEX_SPEED);
      // }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      pcm.shooter.setSpeed(0);
      pcm.index.driveUpper(0);
      pcm.index.driveLower(0);
      Limelight.setLed(LedMode.off);

      if(!interrupted) {
        pcm.intake.putDown();
      }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return pcm.isShooting();
    }
  }
}
