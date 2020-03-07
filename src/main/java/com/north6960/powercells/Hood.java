package com.north6960.powercells;

import com.north6960.Constants.CAN;
import com.north6960.Constants.PID;
import com.north6960.Constants.Physical;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;

public class Hood extends SubsystemBase {

  private CANSparkMax motor;
  private CANPIDController controller;
  private CANEncoder encoder;

  private double setpoint;

  public Hood() {
    motor = new CANSparkMax(CAN.HOOD_MOTOR, MotorType.kBrushless);
    controller = motor.getPIDController();
    encoder = motor.getEncoder();

    encoder.setPositionConversionFactor( 360. / Physical.HOOD_GEAR_RATIO );
    encoder.setPosition(0);
    setpoint = 0;

    controller.setOutputRange(-0.5, 0.5);
    controller.setP(PID.HOOD_P);
    controller.setD(PID.HOOD_D);
    controller.setFF(PID.HOOD_FF);
  }

  public void setBrakeMode(boolean brakeMode) {
    motor.setIdleMode(brakeMode ? IdleMode.kBrake : IdleMode.kCoast);
  }

  public boolean atSetpoint() {
    return encoder.getPosition() >= setpoint - 2. && encoder.getPosition() <= setpoint + 2.;
  }

  public void setAngle(double angDeg) {
    MathUtil.clamp(angDeg, -45, 10);
    this.setpoint = angDeg;
  }
  
  public double getAngle() {
    return encoder.getPosition();
  }

  @Override
  public void periodic() {
    controller.setReference(setpoint, ControlType.kPosition);
  }
}
