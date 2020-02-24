package com.north6960.utils.controller;

import com.north6960.Constants.Button;
import com.north6960.Constants.USB;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The controller used by the Operator that controls game-specific subsystems such as shooters and intakes.
 */
public class OperatorController {
    private static Joystick stickLeft = new Joystick(USB.OPERATOR_JOYSTICK_LEFT);
    private static Joystick stickRight = new Joystick(USB.OPERATOR_JOYSTICK_RIGHT);

    public static JoystickButton shootBtn = new JoystickButton(stickLeft, Button.SHOOT);
    public static JoystickButton shooterManualBtn = new JoystickButton(stickLeft, Button.SHOOTER_MANUAL);

    public static double getX(Hand hand) {
        switch(hand) {
            case kLeft:
                return stickLeft.getX();
            case kRight:
                return stickRight.getX();
            default:
                return 0.0;
        }
    }

    public static double getY(Hand hand) {
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
