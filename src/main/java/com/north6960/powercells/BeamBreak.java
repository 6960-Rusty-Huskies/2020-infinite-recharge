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
    }

    private boolean getRaw() {
        return internalSensor.getVoltage() > 2.5;
    }

    public boolean isTriggered() {
        return getRaw() != defaultState;
    }

    public void set(boolean value) {
        defaultState = value;
    }
}
