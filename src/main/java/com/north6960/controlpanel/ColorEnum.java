package com.north6960.controlpanel;

public enum ColorEnum {
    red, green, blue, yellow;

    private static ColorEnum[] values = values();

    public ColorEnum next(int toIncrease) {
        return values[ (this.ordinal() + toIncrease) % values.length ];
    }
}