package com.north6960.powercells.commands;

import com.north6960.powercells.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ZeroIntakeCommand extends CommandBase {
  Intake intake;
  /**
   * Creates a new ZeroIntakeCommand.
   */
  public ZeroIntakeCommand(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    this.intake = intake;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.armMotor.set(-0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(!interrupted) {
      intake.armMotor.set(0);
      intake.zeroEncoder();
      intake.enable();
      intake.setSetpoint(0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return intake.limitSwitchTriggered();
  }
}
