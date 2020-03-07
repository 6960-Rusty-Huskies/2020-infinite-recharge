package com.north6960.controller; 

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

    public JoystickButton halfSpeedButton;
    public JoystickButton raiseWinchButton;

    public DriverController() {
        // Key is Hand, value is port number
        halfSpeedButton = initButton(Button.DRIVE_HALF_SPEED);
        raiseWinchButton = initButton(Button.RAISE_WINCH);
    }

    public JoystickButton getButton(Hand hand, int value) {
        Joystick stick = null;
        switch(hand) {
            case kLeft:
                stick = leftStick;
                break;
            case kRight:
                stick = rightStick;
                break;
        }

        return new JoystickButton(stick, value);
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
     * <p> Initializes a button on the controller with its Hand and Integer constants. </p>
     * <p> Should be used to initialize each button object in this class only once. </p>
     * @param button The button to initialize.
     * @param constant The constant from Constants.Buttons to initialize the button with.
     */
    private JoystickButton initButton(SimpleEntry<Hand, Integer> constant) {
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

        return new JoystickButton(stick, constant.getValue());
    }
}
