package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveStraight extends Command {

    public AutoDriveStraight(double t) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setTimeout(t);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.driveTrain.autoDriveStraight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try{
    	Robot.driveTrain.autoDriveStraight();
    	} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);

		}
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {

    	System.out.println("I'm done");
    	Robot.driveTrain.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
