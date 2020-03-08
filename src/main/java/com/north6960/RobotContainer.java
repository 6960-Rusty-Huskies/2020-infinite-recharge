package com.north6960;

import com.north6960.Constants.Physical;
import com.north6960.autonomous.DefaultAutoCommand;
import com.north6960.controller.DriverController;
import com.north6960.controller.OperatorController;
import com.north6960.controlpanel.ControlPanelManipulator;
import com.north6960.controlpanel.commands.PositionControl;
import com.north6960.controlpanel.commands.RotationControl;
import com.north6960.drive.DriveBase;
import com.north6960.drive.commands.DriveTeleop;
import com.north6960.generatorswitch.Climber;
import com.north6960.powercells.PowerCellManagement;
import com.north6960.powercells.ShootingType;
import com.north6960.powercells.commands.IntakePowerCells;
import com.north6960.powercells.commands.ShootPowerCells;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Borealis}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private Climber climber = new Climber();
  // private ControlPanelManipulator cpm = new ControlPanelManipulator();
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
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // =-=-=-=-= DRIVER CONTROLS =-=-=-=-= //
    
    driverController.raiseWinchButton
      .whileHeld( () -> climber.driveWinch(0.4) )
      .whenReleased( () -> climber.driveWinch(0.0) );

    driverController.halfSpeedButton
      .whileHeld( () -> driveBase.isHalfSpeed = true )
      .whenReleased( () -> driveBase.isHalfSpeed = false );

    // =-=-=-=-= OPERATOR CONTROLS =-=-=-=-= //

    ShootPowerCells 
      shootFar = new ShootPowerCells(driveBase, powerCellManagement, ShootingType.far),
      shootNear = new ShootPowerCells(driveBase, powerCellManagement, ShootingType.auto);

    opController.shootFarBtn
      .whileActiveOnce(shootFar, true);

    opController.shootNearBtn
      .whileActiveOnce(shootNear, true);

    opController.indexUpBtn
      .whileHeld( () -> {
        powerCellManagement.index.driveBoth(Physical.INDEX_SPEED);
      }, powerCellManagement)
      .whenReleased( () -> {
        powerCellManagement.index.driveBoth(0);
      } );

    opController.indexDownBtn
      .whileHeld( () -> {
        if(!powerCellManagement.index.lower.getBeamBreak()) {
          powerCellManagement.index.driveLower(-Physical.INDEX_SPEED);
        }
        else powerCellManagement.index.driveLower(0);

        powerCellManagement.index.driveUpper(-Physical.INDEX_SPEED);
        powerCellManagement.shooter.setSpeed(-100);
      }, powerCellManagement )
      .whenReleased( () -> {
        powerCellManagement.index.driveBoth(0);
        powerCellManagement.shooter.setSpeed(0);
      } );

      // opController.rotationControlBtn
      //   .toggleWhenPressed(new PositionControl(cpm));

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
    return new DefaultAutoCommand(driveBase, powerCellManagement, new ShootPowerCells(driveBase, powerCellManagement, ShootingType.auto));
  }
}
