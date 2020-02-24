package com.north6960;

import com.north6960.Constants.USB;
import com.north6960.commands.AlternatingPattern;
import com.north6960.drive.DriveBase;
import com.north6960.lights.RGB;
import com.north6960.utils.controller.OperatorController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private Joystick driverStickLeft = new Joystick(USB.DRIVER_JOYSTICK_LEFT);
  private Joystick driverStickRight = new Joystick(USB.DRIVER_JOYSTICK_RIGHT);
  private OperatorController opController = new OperatorController(USB.OPERATOR_JOYSTICK_LEFT);

  private JoystickButton b = new JoystickButton(driverStickLeft, 2);
  
   private RGB m_rgb = new RGB(0, 60); 

  private DriveBase driveBase = new DriveBase(driverStickLeft, driverStickRight);
  
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {                                                                                                                                              
    // Configure the button bindings
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    b.whenPressed(new AlternatingPattern(m_rgb, 12, new Color8Bit(200, 75, 0), new Color8Bit(0, 0, 255)));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
