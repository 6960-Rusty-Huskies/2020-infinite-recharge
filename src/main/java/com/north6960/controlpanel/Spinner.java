package com.north6960.controlpanel;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.north6960.Constants;
import com.north6960.utils.DSColor;
import com.north6960.utils.WheelColor;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The arm and wheel that are used to interface with the Control Panel.
 */
public class Spinner extends SubsystemBase {

  private VictorSPX armMotor, wheelMotor;
  private ColorSensorV3 colorSensor;
  private ColorMatch colorMatch;

  public Spinner() {
    armMotor = new VictorSPX(Constants.SPINNER_ARM_MOTOR);
    wheelMotor = new VictorSPX(Constants.SPINNER_WHEEL_MOTOR);
    
    colorSensor = new ColorSensorV3(Port.kOnboard);

    colorMatch.addColorMatch(WheelColor.red);
    colorMatch.addColorMatch(WheelColor.green);
    colorMatch.addColorMatch(WheelColor.blue);
    colorMatch.addColorMatch(WheelColor.yellow);
  }

  public boolean isColorMatched() {
    if(DSColor.getCurrent() != null) {
      return colorMatch.matchClosestColor(colorSensor.getColor()).color == DSColor.getCurrent();
    }

    return false;
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
