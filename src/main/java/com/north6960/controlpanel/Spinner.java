package com.north6960.controlpanel;

import com.north6960.controlpanel.WheelColor.ColorEnum;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The arm and wheel that are used to interface with the Control Panel.
 */
public class Spinner extends SubsystemBase {

  private ColorSensorV3 colorSensor;
  public SpinnerArm arm;
  public SpinnerWheel wheel;

  private ColorMatch colorMatch;

  public Spinner() {
    colorSensor = new ColorSensorV3(Port.kOnboard);
    arm = new SpinnerArm();
    wheel = new SpinnerWheel();

    colorMatch.addColorMatch(WheelColor.red);
    colorMatch.addColorMatch(WheelColor.green);
    colorMatch.addColorMatch(WheelColor.blue);
    colorMatch.addColorMatch(WheelColor.yellow);
  }

  public Color getDetectedColor() {
    return colorMatch.matchClosestColor(colorSensor.getColor()).color;
  }

  public boolean isColorMatched() {
    return getDetectedColor() == WheelColor.getFMSDisplayedColor();
  }

  public void moveToFMSColor() {
    double speed;

    if(getDetectedColor().toString().isBlank() || getDetectedColor().toString().isEmpty()) {
      performRotationControl();
      return;
    } 

    if(isColorMatched()) speed = 0.0;

    ColorEnum current = WheelColor.ColorEnum.valueOf(getDetectedColor().toString());
    ColorEnum target = ColorEnum.valueOf(WheelColor.getFMSDisplayedColor().toString());

    if((current.ordinal() - 1) % 4 == target.ordinal()) {
      speed = -0.75; // Left
    }

    else speed = 0.75; // Right

    wheel.move(speed);
  }

  public void performRotationControl() {
    
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
