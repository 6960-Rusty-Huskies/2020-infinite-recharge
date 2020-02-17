/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

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
