
package com.north6960;

import java.util.AbstractMap.SimpleEntry;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

	/**
	 * Contains CAN Id constants.
	 */
    public static final class CAN {
        // MOTORS
		public static final int DRIVE_LEFT_FRONT_MOTOR = 1;
		public static final int DRIVE_LEFT_BACK_MOTOR = 2;
		public static final int DRIVE_RIGHT_FRONT_MOTOR = 3;
		public static final int DRIVE_RIGHT_BACK_MOTOR = 4;
		
		public static final int INTAKE_ARM_MOTOR = 8;
		public static final int INTAKE_WHEEL_MOTOR = 7;

		public static final int INDEX_LOWER_MOTOR = 12;
		public static final int INDEX_UPPER_MOTOR = 11;
		
		public static final int SHOOTER_MOTOR = 15;
		public static final int HOOD_MOTOR = 14;
		public static final int LIFT_MOTOR = 5;
		public static final int WINCH_MOTOR = 9;

		public static final int CPM_ARM_MOTOR = 10;
        public static final int CPM_WHEEL_MOTOR = 6;
        
        // IMU
		public static final int PIGEON_IMU = 15; 
	}
	
	/**
	 * Contains DIO port connections to the RoboRIO.
	 */
    public static final class Digital {
        // ENCODERS
		public static final int DRIVE_LEFT_ENCODER_A = 2;
		public static final int DRIVE_LEFT_ENCODER_B = 3;

		public static final int DRIVE_RIGHT_ENCODER_A = 4;
		public static final int DRIVE_RIGHT_ENCODER_B = 5;

		public static final int INTAKE_ENCODER_A = 0;
        public static final int INTAKE_ENCODER_B = 1;

		// LIMIT SWITCHES
		public static final int INTAKE_ARM_LIMIT_SWITCH = 6;
		public static final int LIFT_SWITCH = 10;
		public static final int SPINNER_ARM_LIMIT_SWITCH = 7;
	}
	
	/**
	 * Contains analog port connections to the RoboRIO.
	 */
    public static final class Analog {
        // BEAM BREAKS
		public static final int INDEX_UPPER_BEAM_BREAK = 1;
		public static final int INDEX_LOWER_BEAM_BREAK = 0;
	}
	
    /**
	 * Contains PWM port connections to the RoboRIO.
	 */
    public static final class PWM {
        // LED strips
        public static final int LED_1 = 0;
	}
	
    /**
	 * Contains USB connections (usually controllers) to the laptop running the robot.
	 */
    public static final class USB {
		public static final int DRIVER_JOYSTICK_LEFT = 0;
		public static final int DRIVER_JOYSTICK_RIGHT = 1;

		public static final int OPERATOR_JOYSTICK_LEFT = 2;
		public static final int OPERATOR_JOYSTICK_RIGHT = 3;
	}

	/**
	 * Button constants.
	 */
	public static final class Button {
		// Button values start at 1.

		public static final int SHOOT_ONE = 1;
		public static final int SHOOT_ALL = 2;
		public static final int INTAKE = 3;
		public static final int ROTATION_CONTROL = 4;
		public static final int POSITION_CONTROL = 5;

		public static final int SHOOTER_MANUAL = 6;
		public static final int INDEX_MANUAL = 7;

		public static final SimpleEntry<Hand, Integer> 
			DRIVE_HALF_SPEED = new SimpleEntry<Hand, Integer>(Hand.kRight, 1),
			RAISE_LIFT = new SimpleEntry<Hand, Integer>(Hand.kLeft, 3),
			LOWER_LIFT = new SimpleEntry<Hand, Integer>(Hand.kLeft, 2),
			RAISE_WINCH = new SimpleEntry<Hand, Integer>(Hand.kRight, 3),
			LOWER_WINCH = new SimpleEntry<Hand, Integer>(Hand.kRight, 2);
	}

	/**
	 * Contains physical constants such as gear ratios and RPMs.
	 */
	public static final class Physical {
		public static final double SHOOTER_RPM_DEFAULT = 500;
		public static final double SHOOTER_RPM_NEAR = 1000;
		public static final double SHOOTER_RPM_FAR = 3000;
		public static final double LL_AREA_FAR = 0.1;

		public static final double UPPER_INDEX_SPEED = 200;
		public static final double LOWER_INDEX_SPEED = 200;

		public static final double INTAKE_LOW_ANGLE = 70;
		public static final double INTAKE_HIGH_ANGLE = 0;

		public static final double HOOD_ANGLE_NEAR = 30;
		public static final double HOOD_ANGLE_FAR = 45;
		public static final double HOOD_GEAR_RATIO = 1.;
	}

	/**
	 * Contains PID and feedforward values for subsystems that use a PID controller.
	 */
	public static final class PID {
		
		public static final double SHOOTER_P = 0.005;
		public static final double SHOOTER_FF = 0.0003;

		public static final double INDEX_LOWER_P = 0.0005;
		public static final double INDEX_LOWER_FF = 0.0003;

		public static final double INDEX_UPPER_P = 0.0005;
		public static final double INDEX_UPPER_FF = 0.0003;

		public static final double HOOD_FF = 0.0;
		public static final double HOOD_P = 0.0;
		public static final double HOOD_D = 0.0;

		public static final double DRIVE_BASE_P = 0.09;
		public static final double DRIVE_BASE_D = 0.01;

		public static final double INTAKE_S = 4.52;
		public static final double INTAKE_V = 0.00606;
		public static final double INTAKE_COS = 0.437;
		public static final double INTAKE_A = 9.58E-5;
		public static final double INTAKE_P = 0.000721;
		public static final double INTAKE_D = 0.000339;
	}
}
