package com.north6960.powercells.commands;

import com.north6960.Constants.Physical;
import com.north6960.controller.OperatorController;
import com.north6960.powercells.PowerCellManagement;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakePowerCells extends CommandBase {

  private PowerCellManagement pcm;
  private OperatorController controller;

  /**
   * Creates a new IntakePowerCells.
   */
  public IntakePowerCells(PowerCellManagement pcm) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(pcm);
    this.pcm = pcm;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(!pcm.intake.isDown()) {
      pcm.intake.putDown();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!pcm.index.isManual) {
      if(pcm.index.lower.getBeamBreak()) {
        pcm.index.driveBoth(Physical.INDEX_SPEED);
      }
      else {
        pcm.index.driveBoth(0);
      }
    }

    else {
      pcm.index.driveBoth(Physical.INDEX_SPEED * controller.getY(Hand.kLeft));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pcm.intake.putUp();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
