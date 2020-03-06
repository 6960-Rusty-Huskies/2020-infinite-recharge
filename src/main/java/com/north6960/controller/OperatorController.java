package com.north6960.controller;

import com.north6960.Constants.Button;
import com.north6960.Constants.USB;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The controller used by the Operator that controls game-specific subsystems such as shooters and intakes.
 */
public class OperatorController {
    private GenericHID stickLeft = new Joystick(USB.OPERATOR_JOYSTICK_LEFT);
    private GenericHID stickRight = new Joystick(USB.OPERATOR_JOYSTICK_RIGHT);

    public JoystickButton shootNearBtn = new JoystickButton(stickLeft, Button.SHOOT_NEAR);
    public JoystickButton shootFarBtn = new JoystickButton(stickLeft, Button.SHOOT_FAR);

    public JoystickButton rotationControlBtn = new JoystickButton(stickLeft, Button.ROTATION_CONTROL);
    public JoystickButton positionControlBtn = new JoystickButton(stickLeft, Button.POSITION_CONTROL);

    public JoystickButton toggleIntakeBtn = new JoystickButton(stickLeft, Button.TOGGLE_INTAKE);
    public JoystickButton toggleManualBtn = new JoystickButton(stickLeft, Button.TOGGLE_MANUAL);

    public double getX(Hand hand) {
        switch(hand) {
            case kLeft:
                return stickLeft.getX();
            case kRight:
                return stickRight.getX();
            default:
                return 0.0;
        }
    }

    public double getY(Hand hand) {
        switch(hand) {
            case kLeft:
                return stickLeft.getY();
            case kRight:
                return stickRight.getY();
            default:
                return 0.0;
        }
    }
}
