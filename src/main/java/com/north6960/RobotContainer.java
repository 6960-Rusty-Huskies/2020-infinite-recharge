package com.north6960;

import com.north6960.autonomous.DefaultAutoCommand;
import com.north6960.controller.DriverController;
import com.north6960.controller.OperatorController;
import com.north6960.controlpanel.CPM;
import com.north6960.drive.DriveBase;
import com.north6960.drive.commands.DriveTeleop;
import com.north6960.generatorswitch.Climber;
import com.north6960.powercells.commands.IntakePowerCells;
import com.north6960.powercells.PowerCellManagement;
import com.north6960.powercells.ShootingType;
import com.north6960.powercells.commands.ShootPowerCells;
import com.north6960.powercells.commands.ZeroIntakeCommand;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Borealis}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private Climber climber = new Climber();
  private CPM cpm = new CPM();
  private DriveBase driveBase = new DriveBase(0.75, 0.75);
  public PowerCellManagement powerCellManagement = new PowerCellManagement();

  private DriverController driverController = new DriverController();
  private OperatorController opController = new OperatorController();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {                                                                                                                                 
    // Configure the button bindings
    configureButtonBindings();
    
    powerCellManagement.setDefaultCommand(new IntakePowerCells(powerCellManagement));
    driveBase.setDefaultCommand(new DriveTeleop(driveBase));

    new ZeroIntakeCommand(powerCellManagement.intake).schedule();
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
      .whileHeld( () -> climber.driveLift(0.5) )
      .whenInactive( () -> climber.driveLift(0.0));

    driverController.lowerLiftButton
      .whileHeld( () -> climber.driveLift(-0.1) )
      .whenReleased( () -> climber.driveLift(0.0) );
    
    driverController.raiseWinchButton
      .whileHeld( () -> climber.driveWinch(1.0) )
      .whenReleased( () -> climber.driveWinch(0.0) );

    driverController.lowerWinchButton
      .whileHeld( () -> climber.driveWinch(-1.0) )
      .whenReleased( () -> climber.driveWinch(0.0) ); 
    

    // =-=-=-=-= OPERATOR CONTROLS =-=-=-=-= //

    ShootPowerCells 
      shootFar = new ShootPowerCells(driveBase, powerCellManagement, ShootingType.far),
      shootNear = new ShootPowerCells(driveBase, powerCellManagement, ShootingType.near);

    opController.shootFarBtn
      .toggleWhenPressed(shootFar);

    opController.shootNearBtn
      .toggleWhenPressed(shootNear);

    opController.toggleManualBtn
      .whenPressed( () -> powerCellManagement.toggleManual() );
    
    opController.toggleIntakeBtn
      .whenPressed(new ConditionalCommand( 
        // onTrue
        new InstantCommand(() -> powerCellManagement.intake.toggleArm()), 
        // onFalse
        null, 
        // condition
        powerCellManagement::isManual ));

      // TODO make PositionControl and RotationControl commands.

      // opController.rotationControlBtn
        // .toggleWhenPressed(new PositionControl(cpm));

      // opController.positionControlBtn
      //   .toggleWhenPressed(new RotationControl(cpm));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Borealis} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new DefaultAutoCommand(driveBase, powerCellManagement);
  }
}
