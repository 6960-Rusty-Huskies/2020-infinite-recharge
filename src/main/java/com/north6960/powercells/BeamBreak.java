package com.north6960.powercells;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * A beam break sensor on the robot.
 */
public class BeamBreak {

    private AnalogInput internalSensor;
    private boolean defaultState;

    public BeamBreak(int channel) {
        internalSensor = new AnalogInput(channel);
        defaultState = getRaw();
    }

    public double getVoltage() {
        return internalSensor.getVoltage();
    }

    private boolean getRaw() {
        return internalSensor.getVoltage() > 2.5;
    }

    public boolean isTriggered() {
        return getRaw() != defaultState;
    }

    /**
     * <p> Sets the default state to the current raw state of the beam break. </p>
     * 
     * <p> Should be called when the beam break is known not to be blocked by any object 
     *     it's meant to be tracking. </p>
     */
    public void calibrateDefaultState() {
        defaultState = getRaw();
    }
}
