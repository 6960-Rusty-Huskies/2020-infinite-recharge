package com.north6960.powercells;

import com.north6960.Constants.Physical;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PowerCellManagement extends SubsystemBase {
  public Index index = new Index();
  public Intake intake = new Intake();
  public Shooter shooter = new Shooter();
  public Hood hood = new Hood();

  public PowerCellManagement() {
  }

  /**
   * Prepares the shooter speed and hood angle for shooting. 
   * Values are determined by area of the Limelight target, 
   * i.e. the distance to the target.
   * @param far Whether to set the shooter up to shoot from the trench, i.e. far away.
   */
  public void setUpShooter(ShootingType type) {
    switch(type) {
      case far:
        shooter.setSpeed(Physical.SHOOTER_RPM_FAR);
        hood.setAngle(Physical.HOOD_ANGLE_FAR);
        break;
      case near:
        shooter.setSpeed(Physical.SHOOTER_RPM_NEAR);
        hood.setAngle(Physical.HOOD_ANGLE_NEAR);
        break;
      case auto:
        shooter.setSpeed(Physical.SHOOTER_RPM_AUTO);
        hood.setAngle(Physical.HOOD_ANGLE_AUTO);
    }
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Power Cell Count", index.getPowerCellCount());
    SmartDashboard.putNumber("Current shooter speed", shooter.getSpeed());
    SmartDashboard.putNumber("Current hood angle", hood.getAngle());
  }
}
