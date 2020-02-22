/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
