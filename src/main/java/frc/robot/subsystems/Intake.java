/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class Intake extends PIDSubsystem {

  private VictorSPX wheelMotor, armMotor;
  private Encoder armEncoder;

  /**
   * Creates a new Intake.
   */
  public Intake() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));

    wheelMotor = new VictorSPX(Constants.INTAKE_WHEEL_MOTOR);
    armMotor = new VictorSPX(Constants.INTAKE_ARM_MOTOR);
    armEncoder = new Encoder(Constants.INTAKE_ENCODER_A, Constants.INTAKE_ENCODER_B);

    this.getController().setTolerance(1.);
  }

  public void set(boolean on) {
    if(on) {
      wheelMotor.set(VictorSPXControlMode.Velocity, 0.75);
    }

    else wheelMotor.set(VictorSPXControlMode.Velocity, 0);
  }

  public void raise() {
    setSetpoint(0);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    armMotor.set(VictorSPXControlMode.Velocity, output);
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return armEncoder.getDistance();

  }
}
