/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.north6960.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.north6960.subsystems.controlpanel.Spinner;
import com.north6960.utils.Direction;

public class MoveToColor extends CommandBase {

  private Spinner _spinner;

  public MoveToColor(Spinner spinner) {
    // Use addRequirements() here to declare subsystem dependencies.
    _spinner = spinner;
    addRequirements(spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _spinner.moveArmMotor(Direction.down);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    new WaitCommand(0.5);
    _spinner.moveWheelMotor(Direction.stop);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return _spinner.isColorMatched();
  }
}
