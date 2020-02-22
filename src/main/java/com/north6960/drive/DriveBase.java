package com.north6960.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.north6960.Constants;

public class DriveBase extends SubsystemBase {

  private final DifferentialDrive drive;
  private final PigeonIMU imu;
  private Encoder leftEncoder, rightEncoder;
  private final Joystick rightStick, leftStick;

  /**
   * Creates a new DriveBase.
   */
   public DriveBase(final Joystick leftJoystick, final Joystick rightJoystick) {
      final WPI_TalonSRX leftFront = new WPI_TalonSRX(1);
      final WPI_TalonSRX leftBack = new WPI_TalonSRX(2);
      final SpeedControllerGroup rightGroup = new SpeedControllerGroup(leftFront, leftBack);
  
      final WPI_TalonSRX rightFront = new WPI_TalonSRX(3);
      final WPI_TalonSRX rightBack = new WPI_TalonSRX(4);
      final SpeedControllerGroup leftGroup = new SpeedControllerGroup(rightFront, rightBack);
  
      leftEncoder = new Encoder(2, 3);
      rightEncoder = new Encoder(0, 1);
  


      leftStick = new Joystick(0);
      rightStick = rightJoystick;
  
      drive = new DifferentialDrive(leftGroup, rightGroup);
      imu = new PigeonIMU(leftFront);
    }
  
    @Override
    public void periodic() {
      drive.arcadeDrive(leftStick.getY(),leftStick.getX());
  }


}