package com.north6960.powercells;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * A beam break sensor on the robot.
 */
public class BeamBreak {

    private AnalogInput internalSensor;

    public BeamBreak(int channel) {
        internalSensor = new AnalogInput(channel);
    }

    public double getVoltage() {
        return internalSensor.getVoltage();
    }

    public boolean get() {
        return internalSensor.getVoltage() > 2.5;
    }
}
