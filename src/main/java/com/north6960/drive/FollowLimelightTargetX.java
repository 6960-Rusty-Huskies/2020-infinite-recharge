package com.north6960.drive;

import com.north6960.vision.LedMode;
import com.north6960.vision.Limelight;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class FollowLimelightTargetX extends PIDCommand {
  /**
   * Creates a new FollowLimelightTarget.
   */
  public FollowLimelightTargetX(DriveBase driveBase) {
    super(
        // The controller that the command will use
        new PIDController(0, 0, 0),
        // This should return the measurement
        () -> Limelight.getOffsetX(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          // Use the output here
          driveBase.arcadeDrive(0.0, output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(driveBase);
    Limelight.setLed(LedMode.on);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Finish when the controller is at the setpoint.
    return getController().atSetpoint();
  }

  @Override
  public void end(boolean interrupted) {
    Limelight.setLed(LedMode.off);
  }
}
