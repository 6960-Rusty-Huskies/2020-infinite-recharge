package com.north6960.controlpanel;

import edu.wpi.first.wpilibj.util.Color;

public enum ColorEnum {
    red, green, blue, yellow;

    private static ColorEnum[] values = values();

    public ColorEnum next(int toIncrease) {
        return values[ (this.ordinal() + toIncrease) % values.length ];
    }

    public static ColorEnum fromWheelColor(Color c) {
        if(c == WheelColor.red) return red;
        else if(c == WheelColor.green) return green;
        else if(c == WheelColor.blue) return blue;
        else if(c == WheelColor.yellow) return yellow;
        return null;
    }
}