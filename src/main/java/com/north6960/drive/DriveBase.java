package com.north6960.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;

import com.north6960.Constants.CAN;
import com.north6960.Constants.Digital;

public class DriveBase extends SubsystemBase {

  private final DifferentialDrive drive;
  private final PigeonIMU imu;
  private final Encoder leftEncoder, rightEncoder;
  private final double speedMult, turnMult;
  public boolean isHalfSpeed = false;

  /**
   * Creates a new DriveBase.
   */
  public DriveBase(double speedMultiplier, double turnMultiplier) {

    final WPI_TalonSRX leftFront = new WPI_TalonSRX(CAN.DRIVE_LEFT_FRONT_MOTOR);
    final WPI_TalonSRX leftBack = new WPI_TalonSRX(CAN.DRIVE_LEFT_BACK_MOTOR);
    final SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftFront, leftBack);

    final WPI_TalonSRX rightFront = new WPI_TalonSRX(CAN.DRIVE_RIGHT_FRONT_MOTOR);
    final WPI_TalonSRX rightBack = new WPI_TalonSRX(CAN.DRIVE_RIGHT_BACK_MOTOR);
    final SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightFront, rightBack);

    leftEncoder = new Encoder(Digital.DRIVE_LEFT_ENCODER_A, Digital.DRIVE_LEFT_ENCODER_B);
    rightEncoder = new Encoder(Digital.DRIVE_RIGHT_ENCODER_A, Digital.DRIVE_RIGHT_ENCODER_B);

    leftEncoder.setDistancePerPulse(2048. * Math.PI * 6.);
    rightEncoder.setDistancePerPulse(2048. * Math.PI * 6.);

    drive = new DifferentialDrive(leftGroup, rightGroup);
    imu = new PigeonIMU(leftFront);

    turnMult = turnMultiplier;
    speedMult = speedMultiplier;
  }

  public void arcadeDrive(double speed, double turn) {
    if(isHalfSpeed) {
      speed *= .5;
      turn *= .5;
    }

    speed = MathUtil.clamp(speed, -1.0, 1.0);
    turn = MathUtil.clamp(turn, -1.0, 1.0);
    drive.arcadeDrive(-speed * speedMult, turn * turnMult);
  }

  public double getAverageDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.;
  }

  @Override
  public void periodic() {}
}