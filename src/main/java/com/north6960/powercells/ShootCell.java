package com.north6960.powercells;

import com.north6960.Constants.Physical;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;

public class ShootCell extends CommandBase {
  private PowerCellManagement pcm;

  private final int initialPowerCellCount;
  private int currentPowerCellCount;
  private final int finalPowerCellCount;

  public ShootCell(PowerCellManagement pcm, int numToShoot) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(pcm);
    this.pcm = pcm;

    initialPowerCellCount = pcm.index.getPowerCellCount();

    finalPowerCellCount = initialPowerCellCount - numToShoot;
    MathUtil.clamp(finalPowerCellCount, 0, initialPowerCellCount - 1);

    currentPowerCellCount = initialPowerCellCount;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(pcm.index.getPowerCellCount() == currentPowerCellCount) {
      pcm.index.driveLower(0);
      pcm.index.driveUpper(Physical.UPPER_INDEX_SPEED);
    }

    else if(pcm.index.getPowerCellCount() < currentPowerCellCount) {
      pcm.index.driveUpper(0);
      pcm.index.driveLower(Physical.LOWER_INDEX_SPEED);

      if(pcm.index.getLowerBB()) {
        currentPowerCellCount--;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pcm.shooter.setSpeed(Physical.SHOOTER_RPM_DEFAULT);
    pcm.index.driveUpper(0);
    pcm.index.driveLower(0);

    if(!interrupted) {
      pcm.intake.putDown();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pcm.index.getPowerCellCount() == finalPowerCellCount;
  }
}
