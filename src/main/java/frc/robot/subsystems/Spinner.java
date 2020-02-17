/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.DSColor;
import frc.robot.utils.WheelColor;

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
