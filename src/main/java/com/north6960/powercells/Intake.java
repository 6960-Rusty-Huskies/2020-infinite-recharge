package com.north6960.powercells;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import com.north6960.Constants;

/**
 * The arm which takes in power cells from the outside of the robot and slots them into the Index.
 */
public class Intake extends PIDSubsystem {

  private VictorSPX wheelMotor, armMotor;
  private Encoder armEncoder;

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