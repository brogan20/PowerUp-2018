package org.usfirst.frc.team3695.robot.auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.commands.CyborgCommandDriveDistance;
import org.usfirst.frc.team3695.robot.commands.CyborgCommandDriveUntilError;
import org.usfirst.frc.team3695.robot.commands.CyborgCommandGrow;
import org.usfirst.frc.team3695.robot.commands.CyborgCommandRotateDegrees;
import org.usfirst.frc.team3695.robot.commands.CyborgCommandSpit;
import org.usfirst.frc.team3695.robot.enumeration.Goal;
import org.usfirst.frc.team3695.robot.enumeration.Mast;
import org.usfirst.frc.team3695.robot.enumeration.Position;

import static org.usfirst.frc.team3695.robot.Constants.AutonomousConstants;

/** the sequence of commands for autonomous */
public class CommandGroupAuto extends CommandGroup {

	//Stores the states of the switches and scale
	String gameData;

	public CommandGroupAuto(Position position, Goal goal) {
		//Get the state of the switches and scale for each round
		gameData = DriverStation.getInstance().getGameSpecificMessage();

		// make sure everything is in the right state/position up here
		Robot.SUB_CLAMP.closeArms();


		// EX: making sure flap is closed before auto starts
		switch (position) {
			case LEFT:
				switch (goal){
					case RUN_FOR_IT:
						addSequential(new CyborgCommandDriveUntilError());
						break;
					case SWITCH:
						if (gameData.charAt(0) == 'L'){ //When the switch is on the left
							addSequential(new CyborgCommandDriveDistance(AutonomousConstants.DIST_TO_SWITCH_FROM_SIDE, 5000));
							addSequential(new CyborgCommandGrow(Mast.SCREW_UP));
							addSequential(new CyborgCommandRotateDegrees(AutonomousConstants.ROT_90_CLOCKWISE, 5000));
							addSequential(new CyborgCommandSpit(500L));
						} else { //When the switch is on the right

						}
						break;
					case ENEMY_SWITCH:
                        if (gameData.charAt(2) == 'L'){ //When switch is on the left

                        } else { //When switch is on the right

                        }
						break;
					case SCALE:
						if (gameData.charAt(1) == 'L'){ //When scale is on the left

						} else { //When scale is on the right

						}
						break;

					case BEST_OPTION:
						break;
				}
				break;

			case CENTER:
				switch (goal){
					case RUN_FOR_IT:
						addSequential(new CyborgCommandDriveUntilError());
						break;
					case SWITCH:
						addSequential(new CyborgCommandDriveDistance(AutonomousConstants.DIST_PASS_PORTAL, 5000));
						if (gameData.charAt(0) == 'L'){ //When the switch is on the left
							addSequential(new CyborgCommandRotateDegrees(AutonomousConstants.ROT_90_COUNTERCLOCKWISE, 5000));
							addSequential(new CyborgCommandDriveDistance(AutonomousConstants.DIST_CENTER_LINE_SWITCH_ALIGN, 5000));
							addSequential(new CyborgCommandRotateDegrees(AutonomousConstants.ROT_90_CLOCKWISE, 5000));
							addSequential(new CyborgCommandDriveDistance(AutonomousConstants.DIST_ALLIANCE_WALL_TO_BLOCKS
																				+ AutonomousConstants.DIST_BLOCKS_TO_SWITCH
																				- AutonomousConstants.DIST_PASS_PORTAL, 5000));

						} else { //When the switch is on the right
							addSequential(new CyborgCommandRotateDegrees(AutonomousConstants.ROT_90_CLOCKWISE, 5000));
							addSequential(new CyborgCommandDriveDistance(AutonomousConstants.DIST_CENTER_LINE_SWITCH_ALIGN, 5000));
							addSequential(new CyborgCommandRotateDegrees(AutonomousConstants.ROT_90_COUNTERCLOCKWISE, 5000));
				//			addParallel(new CyborgCommandGoToMid());
							addSequential(new CyborgCommandDriveDistance(AutonomousConstants.DIST_ALLIANCE_WALL_TO_BLOCKS
																				+ AutonomousConstants.DIST_BLOCKS_TO_SWITCH
																				- AutonomousConstants.DIST_PASS_PORTAL, 5000));
						}
						break;
					case ENEMY_SWITCH:
						break;
					case SCALE:
						break;
					case BEST_OPTION:
						break;
			}
				break;

			case RIGHT:
				switch (goal) {
					case RUN_FOR_IT:
						addSequential(new CyborgCommandDriveUntilError());
						break;
					case SWITCH:
						if (gameData.charAt(0) == 'R'){ //When the switch is on the right
							addSequential(new CyborgCommandDriveDistance(AutonomousConstants.DIST_TO_SWITCH_FROM_SIDE, 5000));
							addSequential(new CyborgCommandGrow(Mast.SCREW_UP));
							addSequential(new CyborgCommandRotateDegrees(AutonomousConstants.ROT_90_COUNTERCLOCKWISE, 5000), 1500);
							addSequential(new CyborgCommandSpit(500L));
						} else { //When the switch is on the left

						}
						break;
					case ENEMY_SWITCH:

						break;
					case SCALE:
						if (gameData.charAt(1) == 'R'){ //When scale is on the right

						} else { //When scale is on the left

						}
						break;
					case BEST_OPTION:
						break;
				}
			break;
		}
	}
}