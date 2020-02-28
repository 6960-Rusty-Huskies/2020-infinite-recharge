package com.north6960;

import com.north6960.controller.DriverController;
import com.north6960.controller.OperatorController;
import com.north6960.drive.DriveBase;
import com.north6960.generatorswitch.Climber;
import com.north6960.powercells.PowerCellManagement;
import com.north6960.powercells.ShootCell;
import com.north6960.powercells.ShootPowerCells;
import com.north6960.vision.Limelight;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

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
  private DriveBase driveBase = new DriveBase(0.75, 0.75);
  private PowerCellManagement powerCellManagement;
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

    // =-=-=-=-= DRIVER CONTROLS =-=-=-=-= //

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

    // =-=-=-=-= OPERATOR CONTROLS =-=-=-=-= //

    opController.shootAllBtn
      .whenPressed(new ShootPowerCells(driveBase, powerCellManagement, powerCellManagement.index.getPowerCellCount()));
    
    opController.shootOneBtn
      .whenPressed(new ShootPowerCells(driveBase, powerCellManagement, 1));

    opController.intakeBtn
      .whenPressed( new InstantCommand(() -> powerCellManagement.intake.toggleArm()) );
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
