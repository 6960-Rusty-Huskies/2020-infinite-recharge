package com.north6960.drive.commands;

import com.north6960.Constants.PID;
import com.north6960.drive.DriveBase;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class MoveRobot extends PIDCommand {

  public MoveRobot(DriveBase driveBase, double set) {
    super(
        // The controller that the command will use
        new PIDController(PID.DRIVE_BASE_P, 0, PID.DRIVE_BASE_D),
        // This should return the measurement
        () -> driveBase.getAverageDistance(),
        // This should return the setpoint (can also be a constant)
        () -> set,
        // This uses the output
        output -> {
          // Use the output here
          driveBase.arcadeDrive(output, 0);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(driveBase);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
