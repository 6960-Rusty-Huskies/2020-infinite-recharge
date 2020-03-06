package com.north6960.drive.commands;

import com.north6960.controller.DriverController;
import com.north6960.drive.DriveBase;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveTeleop extends CommandBase {

  private DriveBase driveBase;
  private DriverController controller = new DriverController();

  /**
   * Creates a new ArcadeDrive.
   */
  public DriveTeleop(DriveBase driveBase) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase);
    this.driveBase = driveBase;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = controller.getY(Hand.kLeft);
    double turn = controller.getX(Hand.kRight);

    if(Math.abs(speed) < 0.1) speed = 0;
    if(Math.abs(turn) < 0.1) turn = 0;
    driveBase.arcadeDrive(controller.getY(Hand.kLeft), controller.getX(Hand.kRight));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
