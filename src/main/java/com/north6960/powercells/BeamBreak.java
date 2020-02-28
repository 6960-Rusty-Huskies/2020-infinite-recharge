package com.north6960.powercells;

import edu.wpi.first.wpilibj.AnalogOutput;

/**
 * A beam break sensor on the robot.
 */
public class BeamBreak {

    private AnalogOutput internalSensor;
    private boolean defaultState;

    public BeamBreak(int channel) {
        internalSensor = new AnalogOutput(channel);
        defaultState = getRaw();
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
