package com.north6960.powercells;

import com.north6960.drive.DriveBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PowerCellManagement extends SubsystemBase {

  public Index index;
  public Intake intake;
  public Shooter shooter;
  public Hood hood;

  private boolean isShooting;

  public PowerCellManagement() {
    index = new Index();
    intake = new Intake();
    shooter = new Shooter();
    hood = new Hood();
    isShooting = false;
  }

  public boolean isShooting() {
    return isShooting;
  }

  public void toggleShooting(DriveBase driveBase) {
    isShooting = !isShooting;

    if(!isShooting) {
      CommandScheduler.getInstance().schedule(new ScheduleCommand( new ShootPowerCells(driveBase, this) ));
    }
  }

  /**
   * Prepares the shooter speed and hood angle for shooting. 
   * Values are determined by area of the Limelight target, 
   * i.e. the distance to the target.
   */
  public void setUpShooter() {
    // if(Limelight.getArea() < Physical.LL_AREA_FAR) {
    //   shooter.setSpeed(Physical.SHOOTER_RPM_FAR);
    //   hood.setAngle(Physical.HOOD_ANGLE_FAR);
    // }

    // else if(!Limelight.hasValidTarget() || Limelight.getArea() > Physical.LL_AREA_FAR) {
    //   shooter.setSpeed(Physical.SHOOTER_RPM_NEAR);
    //   hood.setAngle(Physical.HOOD_ANGLE_NEAR);
    // }

    shooter.setSpeed(2000);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Power Cell Count", index.getPowerCellCount());
  }
}
