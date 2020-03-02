package com.north6960.powercells;

import com.north6960.Constants.CAN;
import com.north6960.Constants.PID;
import com.north6960.Constants.Physical;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private CANSparkMax spinnerMotor;
  private CANEncoder encoder;
  private CANPIDController controller;
  private double setpoint;

  public Shooter() {

    spinnerMotor = new CANSparkMax(CAN.SHOOTER_MOTOR, MotorType.kBrushless);
    encoder = spinnerMotor.getEncoder(EncoderType.kHallSensor, 42);
    controller = spinnerMotor.getPIDController();

    controller.setP(PID.SHOOTER_P);
    controller.setFF(PID.SHOOTER_FF);

    spinnerMotor.setInverted(true);
  }

  public void setSpeed(double rpm) {
    setpoint = rpm; 
  }

  public boolean atSetpoint() {
    return encoder.getVelocity() > setpoint - 10;
  }

  @Override
  public void periodic() {
    controller.setReference(setpoint, ControlType.kVelocity);
  }
}
