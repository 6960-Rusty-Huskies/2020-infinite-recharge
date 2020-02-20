package com.north6960.subsystems.controlpanel;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DigitalInput;
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

  private ColorSensorV3 colorSensor;
  public SpinnerArm arm;
  public SpinnerWheel wheel;

  private ColorMatch colorMatch;
  private boolean manualControl;

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
    return getDetectedColor() == WheelColor.getFMSDisplayed();
  }

  public void moveToFMSColor() {
    Direction direction;
    if(isColorMatched()) direction = Direction.stopped;

    ColorEnum current = WheelColor.ColorEnum.valueOf(getDetectedColor().toString());
    ColorEnum target = ColorEnum.valueOf(WheelColor.getFMSDisplayed().toString());

    if((current.ordinal() - 1) % 4 == target.ordinal()) {
      direction = Direction.left;
    }

    else direction = Direction.right;

    wheel.move(direction);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
