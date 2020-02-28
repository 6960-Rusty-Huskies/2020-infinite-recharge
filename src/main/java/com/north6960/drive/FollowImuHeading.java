package com.north6960.drive;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class FollowImuHeading extends PIDCommand {

  /**
   * Creates a new FollowImuHeading.
   */
  public FollowImuHeading(DriveBase driveBase) {
    super(
        // The controller that the command will use
        new PIDController(0, 0, 0),
        // This should return the measurement
        () -> driveBase.getImuCompassHeading(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          // Use the output here
          driveBase.arcadeDrive(0, output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(driveBase);
    getController().setSetpoint(driveBase.getImuCompassHeading());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
