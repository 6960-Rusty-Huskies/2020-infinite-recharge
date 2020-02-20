/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * The set of motors that project Power Cells into the goal.
 */
public class Shooter extends SubsystemBase {

  private double speed;
  private CANSparkMax motor;
  private boolean manualControl = false;

  public Shooter() {
    motor = new CANSparkMax(Constants.SHOOTER_MOTOR, MotorType.kBrushless);
  }

  public void setSpeed(double speedToSet) {
    speed = speedToSet;
  }

  public void set(boolean on) {
    motor.set(on ? speed : 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
