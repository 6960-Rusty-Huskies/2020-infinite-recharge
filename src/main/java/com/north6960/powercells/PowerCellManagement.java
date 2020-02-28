package com.north6960.powercells;

import com.north6960.Constants.Physical;
import com.north6960.vision.Limelight;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PowerCellManagement extends SubsystemBase {

  public Index index;
  public Intake intake;
  public Shooter shooter;
  public Hood hood;

  public PowerCellManagement() {
    index = new Index();
    intake = new Intake();
    shooter = new Shooter();
    hood = new Hood();
  }

  /**
   * Prepares the shooter speed and hood angle for shooting. 
   * Values are determined by area of the Limelight target, 
   * i.e. the distance to the target.
   */
  public void setUpShooter() {
    if(Limelight.getArea() < Physical.LL_AREA_FAR) {
      shooter.setSpeed(Physical.SHOOTER_RPM_FAR);
      hood.setAngle(Physical.HOOD_ANGLE_FAR);
    }

    else if(!Limelight.hasValidTarget() || Limelight.getArea() > Physical.LL_AREA_FAR) {
      shooter.setSpeed(Physical.SHOOTER_RPM_NEAR);
      hood.setAngle(Physical.HOOD_ANGLE_NEAR);
    }
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Power Cell Count", index.getPowerCellCount());
  }
}
