// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveInCircle extends CommandBase {
  private final Drivetrain drivetrain;
  private double speed;

  private double startingAngle;

  /** Creates a new DriveInCircle. */
  public DriveInCircle(Drivetrain drivetrain, double speed) {
    this.drivetrain = drivetrain;
    this.speed = speed;
    this.startingAngle = drivetrain.getGyroAngleZ();
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.arcadeDrive(0, 0);
    drivetrain.resetEncoders();
    startingAngle = drivetrain.getGyroAngleZ();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // The second parameter controls the size of the circle that is turned: 1.0 -> 0 radius, 0.55 -> 24in radius
    drivetrain.arcadeDrive(speed, 0.55); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // trying to travel half a circle
    return (Math.abs(drivetrain.getGyroAngleZ() - startingAngle) > 170 && Math.abs(drivetrain.getGyroAngleZ() - startingAngle) < 190);
  }
}
