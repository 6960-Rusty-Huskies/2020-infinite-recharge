package com.north6960.controlpanel;

import com.north6960.Constants.Digital;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * <p> The arm, wheel and color sensor that are used to manipulate the Control Panel. </p>
 */
public class ControlPanelManipulator extends SubsystemBase {

  private ColorSensorV3 colorSensor;
  private CPMArm arm;
  private CPMWheel wheel;
  private ColorMatch colorMatch;
  private DigitalInput upperLimitSwitch, lowerLimitSwitch;

  public ControlPanelManipulator() {
    colorSensor = new ColorSensorV3(Port.kOnboard);
    arm = new CPMArm();
    wheel = new CPMWheel();
    colorMatch = new ColorMatch();

    upperLimitSwitch = new DigitalInput(Digital.SPINNER_SWITCH_TOP);
    // lowerLimitSwitch = new DigitalInput(Digital.SPINNER_SWITCH_BOTTOM);

    colorMatch.addColorMatch(WheelColor.red);
    colorMatch.addColorMatch(WheelColor.green);
    colorMatch.addColorMatch(WheelColor.blue);
    colorMatch.addColorMatch(WheelColor.yellow);
  }

  public ColorEnum getDetectedColorEnum() {
    Color detected = getDetectedColor();

    if(detected == WheelColor.red) {
      return ColorEnum.red;
    }
    else if(detected == WheelColor.green) {
      return ColorEnum.green;
    }
    else if(detected == WheelColor.blue) {
      return ColorEnum.blue;
    }
    else if(detected == WheelColor.yellow) {
      return ColorEnum.yellow;
    }

    return null;
  }

  public Color getDetectedColor() {
    return colorMatch.matchClosestColor(colorSensor.getColor()).color;
  }

  public boolean isColorMatched() {
    return getDetectedColor() == WheelColor.getFMSDisplayed();
  }

  public void moveTowardFMSColor() {
    double speed;

    if(WheelColor.getFMSDisplayed().toString().isBlank() || WheelColor.getFMSDisplayed().toString().isEmpty()) {
      return;
    } 

    if(isColorMatched()) speed = 0.0;

    // Increase the value of the color by 2 to get the value under the control panel color sensor.
    ColorEnum current = ColorEnum.valueOf(getDetectedColor().toString()).next(2);
    ColorEnum target = ColorEnum.valueOf(WheelColor.getFMSDisplayed().toString());

    if((current.ordinal() - 2) % 4 == target.ordinal()) {
      speed = -0.75; // Left
    }

    else speed = 0.75; // Right

    wheel.move(speed);
  }

  public void moveWheel(double speed) {
    wheel.move(speed);
  }

  public void moveArm(double speed) {
    arm.move(speed);
  }

  public boolean lowerLimitSwitchTriggered() {
    return lowerLimitSwitch.get();
  }

  public boolean upperLimitSwitchTriggered() {
    return upperLimitSwitch.get();
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Color wheel limit switch", arm.limitSwitchTriggered());
  }
}
