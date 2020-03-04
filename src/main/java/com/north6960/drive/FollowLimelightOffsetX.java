package com.north6960.drive;

import com.north6960.Constants.PID;
import com.north6960.powercells.PowerCellManagement;
import com.north6960.vision.Limelight;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class FollowLimelightOffsetX extends PIDCommand {

  private PowerCellManagement pcm; 
  
  /**
   * Creates a new FollowLimelightTarget.
   */
  public FollowLimelightOffsetX(DriveBase driveBase, PowerCellManagement pcm) {
    super(
        // The controller that the command will use
        new PIDController(PID.DRIVE_BASE_P, 0, PID.DRIVE_BASE_D),
        // This should return the measurement
        () -> Limelight.getOffsetX(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          // Use the output here
          driveBase.arcadeDrive(0, -output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(driveBase);
    this.pcm = pcm;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Finish when the controller is at the setpoint.
    return false;
  }

  @Override
  public void end(boolean interrupted) {}
}
