package com.north6960.subsystems.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import com.north6960.Constants;

public class DriveBase extends PIDSubsystem {

  private final DifferentialDrive drive;
  private final PigeonIMU imu;
  private final Joystick rightStick, leftStick;

  /**
   * Creates a new DriveBase.
   */
  public DriveBase(final Joystick leftJoystick, final Joystick rightJoystick) {
    super(
        // The PIDController used by the subsystem
        new PIDController(0.005, 0, 0));

    final WPI_TalonSRX leftFront = new WPI_TalonSRX(Constants.DRIVE_LEFT_FRONT_MOTOR);
    final WPI_TalonSRX leftBack = new WPI_TalonSRX(Constants.DRIVE_LEFT_BACK_MOTOR);
    final SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftFront, leftBack);

    final WPI_TalonSRX rightFront = new WPI_TalonSRX(Constants.DRIVE_RIGHT_FRONT_MOTOR);
    final WPI_TalonSRX rightBack = new WPI_TalonSRX(Constants.DRIVE_RIGHT_BACK_MOTOR);
    final SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightFront, rightBack);

    leftStick = leftJoystick;
    rightStick = rightJoystick;

    drive = new DifferentialDrive(leftGroup, rightGroup);
    imu = new PigeonIMU(leftFront);
  }

  @Override
  public void useOutput(final double output, final double setpoint) {
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}