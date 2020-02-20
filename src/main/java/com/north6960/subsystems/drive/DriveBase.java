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
=======
package com.north6960.subsystems.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants;

/**
 * The base that drives the robot around the field.
 */
public class DriveBase extends SubsystemBase {

  private DifferentialDrive drive;
  private Encoder leftEncoder, rightEncoder;
  private Joystick stickLeft = new Joystick(0);
  private Joystick stickRight = new Joystick(1);

  private double speedMult = 0.9, turnMult = 0.6;

  public DriveBase() {
    WPI_TalonSRX leftFront = new WPI_TalonSRX(Constants.DRIVE_LEFT_FRONT_MOTOR);
    WPI_TalonSRX leftBack = new WPI_TalonSRX(Constants.DRIVE_LEFT_BACK_MOTOR);
    WPI_TalonSRX rightFront = new WPI_TalonSRX(Constants.DRIVE_RIGHT_FRONT_MOTOR);
    WPI_TalonSRX rightBack = new WPI_TalonSRX(Constants.DRIVE_RIGHT_BACK_MOTOR);

    SpeedControllerGroup left = new SpeedControllerGroup(leftFront, leftBack);
    SpeedControllerGroup right = new SpeedControllerGroup(rightFront, rightBack);

    drive = new DifferentialDrive(left, right);

    // leftEncoder = new Encoder(Constants.DRIVE_LEFT_ENCODER_A, Constants.DRIVE_LEFT_ENCODER_B);
    // rightEncoder = new Encoder(Constants.DRIVE_RIGHT_ENCODER_A, Constants.DRIVE_RIGHT_ENCODER_B);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    drive.arcadeDrive(-speedMult * stickLeft.getY(), turnMult * stickRight.getX());
  }
}
