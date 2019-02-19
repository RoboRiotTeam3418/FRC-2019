package frc.robot.auto.actions;
import edu.wpi.first.wpilibj.Timer;

public class WaitAction implements Action {

	double mTimeToWait;
	double mStartTime;
	double mCurrentTime;
	
	public WaitAction(double time){
		mTimeToWait = time;
	}
	
	@Override
	public void start() {
		mStartTime = Timer.getFPGATimestamp();
	}

	@Override
	public void update() {
		mCurrentTime = Timer.getFPGATimestamp();
		System.out.println("time elapsed = " + (mCurrentTime - mStartTime) + " target time = " + mTimeToWait);
	}

	@Override
	public boolean isFinished() {
		if ((mCurrentTime - mStartTime) > mTimeToWait) {
			return true;
		}
		return false;
	}

	@Override
	public void done() {
		
	}

}