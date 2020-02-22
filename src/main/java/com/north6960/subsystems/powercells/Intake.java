package com.north6960.subsystems.powercells;

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
  private boolean isManual;

  public Intake() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0.005, 0, 0));

    wheelMotor = new VictorSPX(Constants.INTAKE_WHEEL_MOTOR);
    armMotor = new VictorSPX(Constants.INTAKE_ARM_MOTOR);
    armEncoder = new Encoder(Constants.INTAKE_ENCODER_A, Constants.INTAKE_ENCODER_B);

    armEncoder.setDistancePerPulse(360. / 2048.); // Gives an output in degrees.

    this.getController().setTolerance(1.);
  }

  public boolean isDown() {
    if(armEncoder.getDistance() > 90) {
      return true;
    } 

    else return false;
  }

  public void setWheel(boolean on) {
    wheelMotor.set(VictorSPXControlMode.PercentOutput, on ? 0.75 : 0);
  }

  public void setArm(double angle) {
    setSetpoint(angle);
  }

  public void setArm(boolean up) {
    setSetpoint(up ? 0 : 90);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    armMotor.set(VictorSPXControlMode.PercentOutput, output);
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return armEncoder.getDistance();
  }
}
