package com.north6960.lights;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class AddressableLEDStrip {

    private AddressableLED strip;
    private AddressableLEDBuffer buffer;
    private Color8Bit[] pattern;
    private double m_brightness = 1.0;
    private final int numLEDs, maxLEDs = 256;

    /**
     * Creates a new LED Strip. Should be individually programmable.
     * @param port The PWM port the strip is plugged into.
     * @param length The number of LEDs in the strip. Should be no more than 256 LEDs.
     */
    public AddressableLEDStrip(int port, int length) {
        if(length > maxLEDs) throw new Error(String.format("Length should be no greater than %s.", maxLEDs));

        numLEDs = length;
        strip = new AddressableLED(port);

        buffer = new AddressableLEDBuffer(numLEDs);
        strip.setLength(numLEDs);
        pattern = new Color8Bit[maxLEDs];

        strip.start();
    }

    public void clear() {
        buffer = new AddressableLEDBuffer(numLEDs);
        pattern = new Color8Bit[maxLEDs];
    }

    public void shiftPatternUp() {
        Color8Bit[] p = pattern.clone();

        for(var i = 1; i < numLEDs - 1; i++) {
            if(p[i - 1] != null) {
                setLED(i, p[i - 1]);
            }
        }

        setLED(0, p[numLEDs - 1]);
    }

    public void shiftPatternDown() {
        Color8Bit[] p = pattern.clone();

        for(var i = numLEDs - 1; i > 1; i--) {
            setLED(i, p[i + 1]);
        }

        setLED(numLEDs, p[0]);
    }

    /**
     * <p> Trims the current pattern of all null values after the last non-null value to supply a continuous pattern. </p>
     * <p> This is an expensive call, and should not be called periodically; only when updating your pattern. </p>
     */
    private void trimPattern() {
        int count = 0;
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] != null && pattern[i + 1] == null) {
                count = i;
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
        m_brightness = brightness;
    }
    
    public void setLED(int index, Color8Bit color) {
        color = new Color8Bit((int) (color.red * m_brightness), (int) (color.green * m_brightness), (int) (color.blue * m_brightness));
        if(index > maxLEDs) throw new Error(String.format("Index should be no greater than %s.", maxLEDs));
        
        pattern[index] = color;

        if(index < numLEDs) {
            buffer.setLED(index, color);
        }
    }

    public void setRange(int startIndex, int endIndex, Color8Bit color) {
        if(endIndex < startIndex) throw new Error("End index cannot be less than start index.");


        for(int i = startIndex; i < endIndex; i++) {
            setLED(i, color);
        }
    }

    public void setAlternating(int width, Color8Bit... colors) {
        int periodWidth = width * (colors.length);

        if(periodWidth > maxLEDs) {
            throw new Error(String.format("Cannot fit %s colors with %s width.", colors.length + 1, width));
        }

        clear();

        for(int i = 0; i < numLEDs / width + width; i++) {
            setRange(i * width, i * width + width, colors[i % colors.length]);
        }

        trimPattern();
    }

    /**
     * Fades from color to color in a list of colors.
     */
    public void setGradient(int width, Color8Bit... colors) {
        clear();

        for(var i = 0; i < maxLEDs / (double) (colors.length * width); i++) {
            for(var j = 0; j < colors.length; j++) {
                final int r1 = colors[j].red;
                final int g1 = colors[j].green;
                final int b1 = colors[j].blue;

                final int r2 = colors[(j + 1) % colors.length].red;
                final int b2 = colors[(j + 1) % colors.length].blue;
                final int g2 = colors[(j + 1) % colors.length].green;

                for (var k = 1; k < width; k++) {
                    Color8Bit color = new Color8Bit( ((r1 + r2) / (k * width)) % 255, ((g1 + g2) / (k * width)) % 255 , ((b1 + b2) / (k * width)) % 255 );
                    setLED(k, color);
                }
            }
        }

        trimPattern();
    }

    public void setSolid(Color8Bit color) {
        clear();
        setRange(0, numLEDs, color);
        trimPattern();
    }
}
