package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionAuto extends CommandGroup {
	
	public VisionAuto() {
		super("Vision-Autonomous root command");
		
		//DigitalInput onOffSwitch = new DigitalInput(RobotMap.DIO_ENABLE_AUTO);
		//DigitalInput shootSwitch = new DigitalInput(RobotMap.DIO_ENABLE_AUTO_SHOOT);
		DigitalInput leftSwitch = new DigitalInput(RobotMap.DIO_AUTO_POS_LEFT);
		DigitalInput rightSwitch = new DigitalInput(RobotMap.DIO_AUTO_POS_RIGHT);
		try{
		// Inverted to deal with internal pullup resistors,
		// so the switch, when on, grounds the DIO (logic 0/false).
		//boolean on = !onOffSwitch.get();
		//boolean shoot = !shootSwitch.get();
		boolean left = !leftSwitch.get();
		boolean right = !rightSwitch.get();
		
		//onOffSwitch.free();
		//shootSwitch.free();
		leftSwitch.free();
		rightSwitch.free();
		
		String status;
		
		//if(on) {
			EnumPosition pos;
			
			if(left && right) throw new RuntimeException(new IllegalStateException("Position switch is both less and right?!?"));
			else if(left) pos = EnumPosition.LEFT;
			else if(right) pos = EnumPosition.RIGHT;
			else pos = EnumPosition.CENTER;
			
			status = "enabled, traveling to the ";
			
			//addSequential(new DriveStraight(RobotMap.AUTO_DRIVE_DIST));
			
			switch(pos) {
				case LEFT: {
					status += "left";
					
					addSequential(new Turn(RobotMap.AUTO_TURN_LEFT));
					break;
				}
				
				case CENTER: {
					status += "center";
					break;
				}
				
				case RIGHT: {
					status += "right";
					
					addSequential(new Turn(RobotMap.AUTO_TURN_RIGHT));
					break;
				}
			}
			
			// Here's the Vision part
			addSequential(new Vision());
			
			//addSequential(new DriveStraight(RobotMap.AUTO_PLACE_GEAR_REVERSE_DIST, true));
			
			/*if(shoot) {
				status += ", and shooting";
				
				addSequential(new WaitCommand(RobotMap.AUTO_WAIT));
				
				switch(pos) {
					case LEFT: {
						addSequential(new DriveToBoilerFromLeft());
						break;
					}
					
					case CENTER: {
						addSequential(new DriveToBoilerFromCenter());
						break;
					}
					
					case RIGHT: {
						addSequential(new DriveToBoilerFromRight());
						break;
					}
				}
				
				addSequential(new SetServo(true));
				addSequential(new StartShooting());
			}*/
		//} else status = "disabled";
		
		System.out.println("Vision Autonomous is " + status + ".");
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);

		}
	}
	
	public static enum EnumPosition { LEFT, RIGHT, CENTER; }
}