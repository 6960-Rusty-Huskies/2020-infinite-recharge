
package com.north6960;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static class CAN {

        // MOTORS
		public static final int DRIVE_LEFT_FRONT_MOTOR = 1;
		public static final int DRIVE_LEFT_BACK_MOTOR = 2;
		public static final int DRIVE_RIGHT_FRONT_MOTOR = 3;
        public static final int DRIVE_RIGHT_BACK_MOTOR = 4;
		public static final int INTAKE_ARM_MOTOR = 5;
		public static final int INTAKE_WHEEL_MOTOR = 6;
		public static final int INDEX_LOWER_MOTOR = 7;
        public static final int INDEX_UPPER_MOTOR = 8;
		public static final int SHOOTER_MOTOR = 9;
		public static final int HOOD_MOTOR = 10;
		public static final int LIFT_MOTOR = 11;
		public static final int WINCH_MOTOR = 12;
		public static final int SPINNER_ARM_MOTOR = 13;
        public static final int SPINNER_WHEEL_MOTOR = 14;
        
        // IMU
		public static final int PIGEON_IMU = 15; 
    }
    
    public static class Digital {
        // ENCODERS
		public static final int DRIVE_LEFT_ENCODER_A = 0;
		public static final int DRIVE_LEFT_ENCODER_B = 1;
		public static final int DRIVE_RIGHT_ENCODER_A = 2;
		public static final int DRIVE_RIGHT_ENCODER_B = 3;
		public static final int INTAKE_ENCODER_A = 4;
        public static final int INTAKE_ENCODER_B = 5;

        // LIMIT SWITCHES
		public static final int LIFT_SWITCH = 6;
		public static final int SPINNER_ARM_SWITCH = 7;
    }

    public static class Analog {
        // BEAM BREAKS
		public static final int INDEX_UPPER_BEAM_BREAK = 13;
		public static final int INDEX_LOWER_BEAM_BREAK = 15;
    }
    
    public static class PWM {
        // LED
        public static final int LED_ID = 0;
    }
    
    public static class USB {
		public static final int DRIVER_JOYSTICK_LEFT = 0;
		public static final int DRIVER_JOYSTICK_RIGHT = 1;
		public static final int OPERATOR_JOYSTICK_LEFT = 2;
		public static final int OPERATOR_JOYSTICK_RIGHT = 3;
    }
}
