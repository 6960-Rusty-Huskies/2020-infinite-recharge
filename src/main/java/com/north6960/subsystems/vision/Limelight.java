package com.north6960.subsystems.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Limelight {
    private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private double height, angle;

    public Limelight(double cameraHeight, double cameraAngle) {
        height = cameraHeight;
        angle = cameraAngle;
    }

    /**
     * @return Whether the limelight has any valid targets.
     */
    public static boolean hasValidTarget() {
        return table.getEntry("tv").getBoolean(false);
    }

    /**
     * @return Vertical Offset From Crosshair To Target (-24.85 to 24.85 degrees).
     */
    public static double getOffsetY() {
        return table.getEntry("ty").getDouble(0.0);
    }

    /**
     * @return Horizontal Offset From Crosshair To Target (-29.8 to 29.8 degrees).
     */
    public static double getOffsetX() {
        return table.getEntry("tx").getDouble(0.0);
    }

    /**
     * @return Target Area (0% of image to 100% of image)
     */
    public static double getArea() {
        return table.getEntry("ta").getDouble(0.0);
    }

    /**
     * @return Skew or rotation (-90 degrees to 0 degrees)
     */
    public static double getSkew() {
        return table.getEntry("ts").getDouble(0.0);
    }

    /**
     * <p> Sets the LimeLight's LED mode. </p>
     * @param ledMode the mode to set the LED to.
     */
    public static void setLed(LedMode ledMode) {
        table.getEntry("ledMode").setNumber(ledMode.ordinal());
    }

    public static void setOperationMode(CameraMode camMode) {
        table.getEntry("camMode").setNumber(camMode.ordinal());
    }

    public double getDistanceToGoal(double goalHeight) {
        return (goalHeight - height) / Math.tan(Math.toRadians(angle + getOffsetY()));
    }

    public static void setCameraStream(StreamType streamType) {
        table.getEntry("stream").setNumber((double) streamType.ordinal()); 
    }

    public static double getCameraStream() {
        return table.getEntry("stream").getDouble(-1.0);
    }
} 