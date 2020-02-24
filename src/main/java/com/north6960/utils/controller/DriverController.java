package com.north6960.utils.controller;

import java.util.AbstractMap.SimpleEntry;

import com.north6960.Constants.Button;
import com.north6960.Constants.USB;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The controller used by the driver which controls a drive base, and often a climbing mechanism.
 */
public class DriverController {

    private Joystick leftStick = new Joystick(USB.DRIVER_JOYSTICK_LEFT);
    private Joystick rightStick = new Joystick(USB.DRIVER_JOYSTICK_RIGHT);

    public JoystickButton halfSpeedButton, raiseLiftButton, lowerLiftButton;

    public DriverController() {
        // Key is Hand, value is port number
        initButton(halfSpeedButton, Button.DRIVE_HALF_SPEED);
        initButton(raiseLiftButton, Button.RAISE_LIFT);
        initButton(lowerLiftButton, Button.LOWER_LIFT);
    }

    public double getX(Hand hand) {
        switch(hand) {
            case kLeft:
                return leftStick.getX();
            case kRight:
                return rightStick.getX();
            default:
                return 0;
        }
    }

    public double getY(Hand hand) {
        switch(hand) {
            case kLeft:
                return leftStick.getY();
            case kRight:
                return rightStick.getY();
            default:
                return 0;
        }
    }

    /**
     * <p> Initializes a button on the controller using Hand Integer constants. </p>
     * <p> Should be used to initialize each button object in this class only once. </p>
     * @param button The button to initialize.
     * @param constant The constant from Constants.Buttons to initialize the button with.
     */
    private void initButton(JoystickButton button, SimpleEntry<Hand, Integer> constant) {
        Joystick stick;
        switch(constant.getKey()) {
            case kLeft:
                stick = leftStick;
                break;
            case kRight:
                stick = rightStick;
                break;
            default:
                stick = null;
                break;
        }

        button = new JoystickButton(stick, constant.getValue());
    }
}
