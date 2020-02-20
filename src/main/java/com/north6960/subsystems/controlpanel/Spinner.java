/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.north6960.subsystems.controlpanel;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.north6960.Constants;
import com.north6960.utils.Direction;
import com.north6960.subsystems.controlpanel.WheelColor.ColorEnum;

/**
 * The arm and wheel that are used to interface with the Control Panel.
 */
public class Spinner extends SubsystemBase {

  private VictorSPX armMotor, wheelMotor;
  private ColorSensorV3 colorSensor;
  private ColorMatch colorMatch;
  private double armSpeed, wheelSpeed;
  private boolean manualControl;

  public Spinner() {
    armMotor = new VictorSPX(Constants.SPINNER_ARM_MOTOR);
    wheelMotor = new VictorSPX(Constants.SPINNER_WHEEL_MOTOR);

    colorSensor = new ColorSensorV3(Port.kOnboard);

    colorMatch.addColorMatch(WheelColor.red);
    colorMatch.addColorMatch(WheelColor.green);
    colorMatch.addColorMatch(WheelColor.blue);
    colorMatch.addColorMatch(WheelColor.yellow);
  }

  public Color getDetectedColor() {
    return colorMatch.matchClosestColor(colorSensor.getColor()).color;
  }

  public boolean isColorMatched() {
    if (WheelColor.getFMSDisplayed() != null) {
      return getDetectedColor() == WheelColor.getFMSDisplayed();
    }

    return false;
  }

  public void moveWheelMotor(Direction direction) {
    switch (direction) {
      case left:
        wheelMotor.set(VictorSPXControlMode.PercentOutput, -wheelSpeed);
        break;
      case right:
        wheelMotor.set(VictorSPXControlMode.PercentOutput, wheelSpeed);
        break;
      case stop:
        wheelMotor.set(VictorSPXControlMode.PercentOutput, 0.0);
      case up:
      case down:
        break;
    }
  }

  public Direction determinePositionControlDirection() {
    if(isColorMatched()) return Direction.stop;

    ColorEnum current = WheelColor.ColorEnum.valueOf(getDetectedColor().toString());
    ColorEnum target = ColorEnum.valueOf(WheelColor.getFMSDisplayed().toString());

    if((current.ordinal() - 1) % 4 == target.ordinal()) {
      return Direction.left;
    }

    return Direction.right;
  }

  public void moveArmMotor(Direction direction) {
    switch(direction) {
      case up:
        armMotor.set(VictorSPXControlMode.PercentOutput, -armSpeed);
        break;
      case down:
        armMotor.set(VictorSPXControlMode.PercentOutput, armSpeed);
        break;
      case stop:
        armMotor.set(VictorSPXControlMode.PercentOutput, 0.0);
        break;
      case left:
      case right:
        break;
    }
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
