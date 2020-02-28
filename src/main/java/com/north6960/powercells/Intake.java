package com.north6960.powercells;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.north6960.Constants.CAN;
import com.north6960.Constants.Digital;
import com.north6960.Constants.PID;
import com.north6960.Constants.Physical;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/**
 * The arm which takes in power cells from the outside of the robot and slots them into the Index.
 */
public class Intake extends PIDSubsystem {

  private VictorSPX wheelMotor, armMotor;
  private Encoder armEncoder;
  private ArmFeedforward feedforward;

  public Intake() {
    super(
        // The PIDController used by the subsystem
        new PIDController(PID.INTAKE_P, 0, PID.INTAKE_D));

    feedforward = new ArmFeedforward(PID.INTAKE_S, PID.INTAKE_V, PID.INTAKE_COS, PID.INTAKE_A);

    wheelMotor = new VictorSPX(CAN.INTAKE_WHEEL_MOTOR);
    armMotor = new VictorSPX(CAN.INTAKE_ARM_MOTOR);
    armEncoder = new Encoder(Digital.INTAKE_ENCODER_A, Digital.INTAKE_ENCODER_B);

    // Encoder should give an output in degrees.
    armEncoder.setDistancePerPulse(360. / 2048.);
    getController().setTolerance(1.);
  }

  public boolean isDown() {
    return getController().getSetpoint() == Physical.INTAKE_LOW_ANGLE;
  }

  public boolean isUp() {
    return getController().getSetpoint() == Physical.INTAKE_HIGH_ANGLE;
  }
  
  public void setWheel(boolean on) {
    wheelMotor.set(VictorSPXControlMode.PercentOutput, on ? 0.75 : 0.0);
  }

  public void toggleArm() {
    if(this.getController().getSetpoint() == 0) {
      putUp();
    }

    else putDown();
  }

  public void putUp() {
    setSetpoint(Physical.INTAKE_HIGH_ANGLE);
    setWheel(false);
  }

  public void putDown() {
    setSetpoint(Physical.INTAKE_LOW_ANGLE);
    setWheel(true);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    armMotor.set(VictorSPXControlMode.Position, 
      output + feedforward.calculate(Math.toRadians(setpoint), Math.PI / 2));
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return armEncoder.getDistance();
  }
}
