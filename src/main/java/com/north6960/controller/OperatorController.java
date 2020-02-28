package com.north6960.controller;

import com.north6960.Constants.Button;
import com.north6960.Constants.USB;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The controller used by the Operator that controls game-specific subsystems such as shooters and intakes.
 */
public class OperatorController {
    private Joystick stickLeft = new Joystick(USB.OPERATOR_JOYSTICK_LEFT);
    private Joystick stickRight = new Joystick(USB.OPERATOR_JOYSTICK_RIGHT);

    public JoystickButton shootOneBtn = new JoystickButton(stickLeft, Button.SHOOT_ONE);
    public JoystickButton shootAllBtn = new JoystickButton(stickLeft, Button.SHOOT_ALL);
    public JoystickButton intakeBtn = new JoystickButton(stickLeft, Button.INTAKE);

    public JoystickButton shooterManualBtn = new JoystickButton(stickLeft, Button.SHOOTER_MANUAL);

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
