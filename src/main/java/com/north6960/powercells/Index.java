package com.north6960.powercells;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.controller.ElevatorFeedforward;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants.Analog;
import com.north6960.Constants.CAN;
import com.north6960.controller.DriverController;

/**
 * The "holding chamber" for power cells waiting to be shot.
 */
public class Index extends SubsystemBase {
  private IndexSection lower, upper;
  private int powerCellCount;

  DriverController controller = new DriverController();

  public Index() {
    lower = new IndexSection(Analog.INDEX_LOWER_BEAM_BREAK, CAN.INDEX_LOWER_MOTOR);
    upper = new IndexSection(Analog.INDEX_UPPER_BEAM_BREAK, CAN.INDEX_UPPER_MOTOR);

    upper.enable();
    upper.setSetpoint(0.0);
    lower.enable();
    lower.setSetpoint(100.0);

    SmartDashboard.putNumber("Lower speed", 0.0);
    SmartDashboard.putNumber("Upper speed", 0.0);
  }

  public int getPowerCellCount() {
    return powerCellCount;
  }

  public void driveUpper(double speed) {
    upper.drive(speed);
  }

  public void driveLower(double speed) {
    lower.drive(speed);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("getDistance", upper.getMeasurement());
    SmartDashboard.putBoolean("PID enabled", upper.isEnabled());
    double dL = SmartDashboard.getNumber("Lower speed", 0.0);
    double dU = SmartDashboard.getNumber("Upper speed", 0.0);
    // This method will be called once per scheduler run
    /*
    if (lower.ballPassed(true)) {
      powerCellCount++;
    }
    if (upper.ballPassed(false)) {
      powerCellCount--;
    }
    */
    // lower.drive(dL);
    // upper.drive(dU);
  }
}
