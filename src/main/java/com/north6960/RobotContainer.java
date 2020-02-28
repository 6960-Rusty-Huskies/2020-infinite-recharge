package com.north6960;

import com.north6960.controller.DriverController;
import com.north6960.controller.OperatorController;
import com.north6960.generatorswitch.Climber;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final DriverController driverController = new DriverController();
  // private final OperatorController opController = new OperatorController();
  private Climber climber = new Climber();
  private DriverController driverController = new DriverController();
  private OperatorController opController = new OperatorController();

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
    driverController.raiseLiftButton
      .whenPressed( () -> climber.lift.move(1.0) )
      .whenReleased( () -> climber.lift.move(0.0) );

    driverController.lowerLiftButton
      .whenPressed( () -> climber.lift.move(-1.0) )
      .whenReleased( () -> climber.lift.move(0.0) );
    
    driverController.raiseWinchButton
      .whenPressed( () -> climber.winch.move(1.0) )
      .whenReleased( () -> climber.winch.move(0.0) );

    driverController.lowerWinchButton
      .whenPressed( () -> climber.winch.move(-1.0) )
      .whenReleased( () -> climber.winch.move(0.0) );
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
