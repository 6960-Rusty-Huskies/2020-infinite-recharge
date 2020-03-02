package com.north6960.powercells;

import com.north6960.Constants.CAN;
import com.north6960.Constants.PID;
import com.north6960.controller.DriverController;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hood extends SubsystemBase {

  private CANSparkMax motor;
  private CANPIDController controller;
  private CANEncoder encoder;

  private DriverController dController = new DriverController();

  private double angDeg;

  public Hood() {
    motor = new CANSparkMax(CAN.HOOD_MOTOR, MotorType.kBrushless);
    controller = motor.getPIDController();
    encoder = motor.getEncoder();

    encoder.setPositionConversionFactor(1. / 360.);
    angDeg = encoder.getPosition();

    controller.setOutputRange(-0.5, 0.5);
    controller.setP(PID.HOOD_P);
    controller.setFF(PID.HOOD_FF);
  }

  public void setAngle(double angDeg) {
    this.angDeg = angDeg;
  }

  @Override
  public void periodic() {
    controller.setReference(angDeg, ControlType.kPosition);
  }
}
