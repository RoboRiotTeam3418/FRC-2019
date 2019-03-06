package frc.robot.auto.modes;
import frc.robot.auto.AutoModeBase;
import frc.robot.auto.AutoModeEndedException;
import frc.robot.auto.actions.DriveStraightActionTime;
import frc.robot.auto.actions.OuttakeHatchAction;
import frc.robot.auto.actions.SetElevatorPositionAction;
import frc.robot.auto.actions.WaitAction;


public class DeliverHatchMiddlePosition extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {

		runAction(new SetElevatorPositionAction("HATCH", "MIDDLE"));
		runAction(new WaitAction(1));
		runAction(new DriveStraightActionTime(.5, true, .25));
		runAction(new WaitAction(1));
		runAction(new OuttakeHatchAction(1));
		runAction(new WaitAction(1));
		runAction(new DriveStraightActionTime(.5, false, .25));
		runAction(new WaitAction(1));
		runAction(new SetElevatorPositionAction("HATCH", "LOW"));
		
	}

}