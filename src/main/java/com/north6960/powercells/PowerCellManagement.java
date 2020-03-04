package com.north6960.powercells;

import com.north6960.RobotContainer;
import com.north6960.drive.DriveBase;
import com.north6960.vision.LedMode;
import com.north6960.vision.Limelight;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PowerCellManagement extends SubsystemBase {

  public Index index = new Index();
  public Intake intake = new Intake();
  public Shooter shooter = new Shooter();
  public Hood hood = new Hood();

  private Command shootCommand = new ShootPowerCells(RobotContainer.driveBase, this);
  private boolean isShooting = false;

  public PowerCellManagement() {
    SmartDashboard.putNumber("Shooter RPM", 0);
    SmartDashboard.putNumber("Hood angle", 0);
  }

  public boolean isShooting() {
    return isShooting;
  }

  public void toggleShooting(DriveBase driveBase) {
    isShooting = !isShooting;

    if(isShooting) {
      shootCommand.schedule();
      Limelight.setLed(LedMode.on);
    }

    else {
      shootCommand.cancel();
      
      shooter.setSpeed(0);
      index.driveUpper(0);
      index.driveLower(0);
      Limelight.setLed(LedMode.off);
    }
  }

  /**
   * Prepares the shooter speed and hood angle for shooting. 
   * Values are determined by area of the Limelight target, 
   * i.e. the distance to the target.
   */
  public void setUpShooter() {
    // if(Limelight.getArea() < Physical.LL_AREA_FAR) {
      shooter.setSpeed(SmartDashboard.getNumber("Shooter RPM", 0));
      hood.setAngle(SmartDashboard.getNumber("Hood angle", 0));
    // }

    // else if(!Limelight.hasValidTarget() || Limelight.getArea() > Physical.LL_AREA_FAR) {
      // shooter.setSpeed(Physical.SHOOTER_RPM_NEAR);
      // hood.setAngle(Physical.HOOD_ANGLE_NEAR);
    // }
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Power Cell Count", index.getPowerCellCount());
    
    SmartDashboard.putNumber("Current shooter speed", shooter.getSpeed());
    SmartDashboard.putNumber("Current hood angle", hood.getAngle());
  }
}
