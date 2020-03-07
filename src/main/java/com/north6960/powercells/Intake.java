package com.north6960.powercells;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.north6960.Constants.CAN;
import com.north6960.Constants.Digital;
import com.north6960.controller.OperatorController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;

/**
 * The arm which takes in power cells from the outside of the robot and slots them into the Index.
 */
public class Intake extends SubsystemBase {

  public WPI_VictorSPX wheelMotor, armMotor;

  private DigitalInput limitSwitch;
  private double speedMult = 0.45;
  private OperatorController controller;

  public Intake() {
    wheelMotor = new WPI_VictorSPX(CAN.INTAKE_WHEEL_MOTOR);
    armMotor = new WPI_VictorSPX(CAN.INTAKE_ARM_MOTOR);
    limitSwitch = new DigitalInput(Digital.INTAKE_ARM_LIMIT_SWITCH);

    wheelMotor.setInverted(true);

    controller = new OperatorController();
  }

  public boolean limitSwitchTriggered() {
    return limitSwitch.get();
  }

  public void driveWheel(double speed) {
    MathUtil.clamp(speed, -1, 1);
    speed *= speedMult;
    wheelMotor.set(speed);
  }
  
  public void setWheel(boolean on) {
    wheelMotor.set(VictorSPXControlMode.PercentOutput, on ? speedMult : 0);
  }

  @Override
  public void periodic() {
    double armSpeed = speedMult * controller.getY(Hand.kLeft);
    if(!limitSwitchTriggered() || armSpeed > 0) {
      armMotor.set(armSpeed);
      wheelMotor.set(speedMult * controller.getX(Hand.kLeft));
    }

    SmartDashboard.putBoolean("Intake limit switch", limitSwitchTriggered());
  }
}
