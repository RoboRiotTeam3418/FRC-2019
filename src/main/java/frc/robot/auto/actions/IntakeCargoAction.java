package frc.robot.auto.actions;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Timer;

public class IntakeCargoAction implements Action  
{
    private Intake mIntake = Intake.getInstance();

    private boolean finished = false;
    private double mTimeToWait;

	private double mStartTime;

	private double mCurrentTime;

    
    public IntakeCargoAction(double time)
    {
        finished = false;
        mTimeToWait = time;
    }

    @Override
    public void start()
    {
       mIntake.intakeCargo();
    }
    
    public void update()
    {

        mCurrentTime = Timer.getFPGATimestamp();
        
    }
    
    public boolean isFinished()
    {
        if ((mCurrentTime - mStartTime) > mTimeToWait) {

			return true;

		}

		return false;

    }
    
    public void done()
    {

    }

}