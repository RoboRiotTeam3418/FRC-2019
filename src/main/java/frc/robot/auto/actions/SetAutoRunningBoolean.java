package frc.robot.auto.actions;
import frc.robot.Setup;

public class SetAutoRunningBoolean implements Action  
{
    Setup mSetup;
    
    @Override
    public void start()
    {
        
        mSetup.AutoRunning = false;

    }
    
    public void update()
    {

        
    }
    
    public boolean isFinished()
    {
        return true;
    }
    
    public void done()
    {

    }

}