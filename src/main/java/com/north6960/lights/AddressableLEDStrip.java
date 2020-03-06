package com.north6960.lights;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpiutil.math.MathUtil;

/**
 * Class for interfacing with addressable LED strips connected to a RoboRIO.
 */
public class AddressableLEDStrip {

    private AddressableLED strip;
    private AddressableLEDBuffer buffer;
    private Color8Bit[] pattern;
    private double brightness = 1.0;
    private final int length, maxLength = 1024;

    /**
     * Creates a new LED Strip. Should be individually programmable.
     * @param port The PWM port the strip is plugged into.
     * @param length The number of LEDs in the strip. Should be no more than 256 LEDs.
     */
    public AddressableLEDStrip(int port, int length) {
        if(length > maxLength) throw new Error(String.format("Length should be no greater than %s.", maxLength));

        this.length = length;
        strip = new AddressableLED(port);
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(length);
        pattern = new Color8Bit[maxLength];

        strip.start();
    }

    public void clear() {
        buffer = new AddressableLEDBuffer(length);
        pattern = new Color8Bit[maxLength];
    }

    public void shiftPatternUp() {
        var temp = pattern[0];

        for(var i = 0; i < length - 1; i++) {
            setLED(i, pattern[i + 1]);
        }

        setLED(pattern.length-1, temp);
    }

    public void shiftPatternDown() {
        var temp = pattern[pattern.length-1];

        for(var i = pattern.length - 1; i > 0; i--) {
            setLED(i, pattern[i - 1]);
        }

        setLED(0, temp);
    }

    /**
     * Trims the current pattern of all null values after the last non-null value to supply a continuous pattern.
     */
    private void trimPattern() {
        int count = 0;

        for(int i = 0; i < pattern.length; i++) {
            if(i > length && pattern[i] != null && pattern[i+1] == null){
                count = i;
            }
            else if(i < length && pattern[i] == null) {
                pattern[i] = new Color8Bit(0, 0, 0);
            }
        }

        Color8Bit[] newPattern = new Color8Bit[count];

        for(int i = 0; i < count; i++) {
            newPattern[i] = pattern[i];
        }

        pattern = newPattern;
    }
    
    /**
     * Updates the LED strip with new information. Should be called periodically.
     */
    public void update() {
        strip.setData(buffer);
    }

    public void setBrightness(double brightness) {
        brightness = MathUtil.clamp(brightness, 0.0, 1.0);

        this.brightness = brightness;
    }
    
    public void setLED(int index, Color8Bit color) {
        if(index > maxLength) {
            throw new Error(String.format("Index should be no greater than %s.", maxLength));
        }

        pattern[index] = color;

        if(index < length) {
            Color8Bit c = 
                new Color8Bit((int) (color.red * brightness), 
                              (int) (color.green * brightness), 
                              (int) (color.blue * brightness));

            buffer.setLED(index, c);
        }
    }

    public void setLED(int index, int r, int g, int b) {
        setLED(index, new Color8Bit(r, g, b));
    }

    public void setRange(int startIndex, int endIndex, Color8Bit color) {
        if(endIndex <= startIndex) throw new Error("End index must be greater than start index.");

        for(int i = startIndex; i < endIndex; i++) {
            setLED(i, color);
        }
    }

    public void setAlternating(int width, Color8Bit... colors) {
        if(colors.length < 2) {
            throw new Error("Color list should contain more than one color.");
        }

        int periodWidth = width * (colors.length);

        if(periodWidth > maxLength) {
            throw new Error(String.format("Cannot fit %s colors with %s width.", colors.length + 1, width));
        }

        clear();

        for(int i = 0; i < length / width + 1; i++) {
            setRange(i * width, i * width + width, colors[i % colors.length]);
        }

        trimPattern();
    }

    /**
     * Creates a gradient on the LED strip that fades from color to color.
     * @param steps The width of each gradient.
     */
    public void setGradient(int steps, Color8Bit... colors) {
        if(colors.length < 2) {
            throw new Error("Color list should contain more than 1 color.");
        }

        clear();
        for (int i = 0; i < colors.length; i++) {
            double percentage = 1 / (double) steps;
            int index = i * steps;

            for (double p = 1.0; p >= 0.0; p -= percentage) {
                Color8Bit from = colors[i % colors.length];
                Color8Bit to = colors[(i + 1) % colors.length];

                int r = (int) (from.red * p + to.red * (1.0 - p));
                int g = (int) (from.green * p + to.green * (1.0 - p));
                int b = (int) (from.blue * p + to.blue * (1.0 - p));

                setLED(index, r, g, b);
                index++;
            }
        }

        // trimPattern();
    }

    public void setSolid(Color8Bit color) {
        clear();
        setRange(0, length, color);
        trimPattern();
    }

    public void setSolid(int r, int g, int b) {
        setSolid(new Color8Bit(r, g, b));
    }
}
