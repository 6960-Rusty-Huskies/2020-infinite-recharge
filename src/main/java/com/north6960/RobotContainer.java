package com.north6960;

import com.north6960.controller.DriverController;
import com.north6960.controller.OperatorController;
import com.north6960.controlpanel.CPM;
import com.north6960.drive.DriveBase;
import com.north6960.drive.DriveTeleop;
import com.north6960.generatorswitch.Climber;
import com.north6960.powercells.Index;
import com.north6960.powercells.IntakePowerCells;
import com.north6960.powercells.PowerCellManagement;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private Climber climber = new Climber();
  private CPM cpm = new CPM();
  public static DriveBase driveBase = new DriveBase(1.0, 1.0);
  public static PowerCellManagement powerCellManagement = new PowerCellManagement();

  private DriverController driverController = new DriverController();
  private OperatorController opController = new OperatorController();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {                                                                                                                                 
    // Configure the button bindings
    configureButtonBindings();
    
    powerCellManagement.setDefaultCommand(new IntakePowerCells(powerCellManagement));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // =-=-=-=-= DRIVER CONTROLS =-=-=-=-= //

    driveBase.setDefaultCommand(new DriveTeleop(driveBase));

    // driverController.raiseLiftButton
    //   .whileHeld( () -> climber.lift.move(0.25) )
    //   .whenInactive( () -> climber.lift.move(0.0));

    // driverController.lowerLiftButton
    //   .whileHeld( () -> climber.lift.move(-0.1) )
    //   .whenInactive( () -> climber.lift.move(0.0) );
    
    // driverController.raiseWinchButton
    //   .whileHeld( () -> climber.winch.move(1.0) )
    //   .whenReleased( () -> climber.winch.move(0.0) );

    // driverController.lowerWinchButton
    //   .whileHeld( () -> climber.winch.move(-1.0) )
    //   .whenReleased( () -> climber.winch.move(0.0) ); 
    

    // =-=-=-=-= OPERATOR CONTROLS =-=-=-=-= //

    driverController.getButton(Hand.kLeft, 1)
      .whenPressed( () -> powerCellManagement.toggleShooting(driveBase) );
    
    driverController.getButton(Hand.kLeft, 2)
      .whileHeld( () -> powerCellManagement.intake.armMotor.set(0.5) )
      .whenReleased( () -> powerCellManagement.intake.armMotor.set(0) );

    driverController.getButton(Hand.kLeft, 3)
      .whileHeld( () -> powerCellManagement.intake.armMotor.set(-0.5))
      .whenReleased( () -> powerCellManagement.intake.armMotor.set(0) );
    
    driverController.getButton(Hand.kRight, 3)
      .whileHeld( () -> powerCellManagement.intake.wheelMotor.set(0.45) )
      .whenReleased( () -> powerCellManagement.intake.wheelMotor.set(0) );

    driverController.getButton(Hand.kRight, 2)
      .whileHeld( () -> powerCellManagement.intake.wheelMotor.set(-0.45) )
      .whenReleased( () -> powerCellManagement.intake.wheelMotor.set(0) );

    driverController.getButton(Hand.kRight, 6)
      .whileHeld( () -> climber.lift.move(0.2) )
      .whenReleased( () -> climber.lift.move(0) );

    driverController.getButton(Hand.kRight, 7)
      .whileHeld( () -> climber.lift.move(-0.2) )
      .whenReleased( () -> climber.lift.move(0) );
    
      driverController.getButton(Hand.kLeft, 11)
        .whileHeld( () -> climber.winch.move(-1.0) )
        .whenReleased( () -> climber.winch.move(0) );

    // opController.shootAllBtn
    //   .toggleWhenPressed(
    //     new ShootPowerCells(driveBase, powerCellManagement, 
    //     powerCellManagement.index.getPowerCellCount())
    //     .andThen(
    //       new IntakePowerCells(powerCellManagement)));

    
    // opController.intakeBtn
    //   .whenPressed( new InstantCommand(() -> powerCellManagement.intake.toggleArm()) );
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
