/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils.controller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The controller used by the Operator that controls game-specific subsystems such as shooters and intakes.
 */
public class OperatorController {
    private Joystick stick;
    public JoystickButton[] button;

    public OperatorController(int port) {
        stick = new Joystick(port);
        button = new JoystickButton[stick.getButtonCount()];

        for(int i = 1; i <= stick.getButtonCount(); i++) {
            button[i] = new JoystickButton(stick, i);
        }
    }

    public double getAxis(int axis) {
        return stick.getRawAxis(axis);
    }
}
