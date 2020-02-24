package com.north6960.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants.CAN;
import com.north6960.Constants.Digital;
import com.north6960.Constants.USB;

public class DriveBase extends SubsystemBase {

  private final DifferentialDrive drive;
  private final PigeonIMU imu;
  private final Encoder leftEncoder, rightEncoder;
  private final Joystick rightStick;
  private final Joystick leftStick;

  /**
   * Creates a new DriveBase.
   */
  public DriveBase() {

    final WPI_TalonSRX leftFront = new WPI_TalonSRX(CAN.DRIVE_LEFT_FRONT_MOTOR);
    final WPI_TalonSRX leftBack = new WPI_TalonSRX(CAN.DRIVE_LEFT_BACK_MOTOR);
    final SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftFront, leftBack);

    final WPI_TalonSRX rightFront = new WPI_TalonSRX(CAN.DRIVE_RIGHT_FRONT_MOTOR);
    final WPI_TalonSRX rightBack = new WPI_TalonSRX(CAN.DRIVE_RIGHT_BACK_MOTOR);
    final SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightFront, rightBack);

    leftEncoder = new Encoder(Digital.DRIVE_LEFT_ENCODER_A, Digital.DRIVE_LEFT_ENCODER_B);
    rightEncoder = new Encoder(Digital.DRIVE_RIGHT_ENCODER_A, Digital.DRIVE_RIGHT_ENCODER_B);

    rightStick = new Joystick(USB.DRIVER_JOYSTICK_RIGHT);
    leftStick = new Joystick(USB.DRIVER_JOYSTICK_LEFT);

    drive = new DifferentialDrive(leftGroup, rightGroup);
    imu = new PigeonIMU(leftFront);
  }

  @Override
  public void periodic() {
  }
}