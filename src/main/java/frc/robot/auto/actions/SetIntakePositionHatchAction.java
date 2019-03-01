package frc.robot.auto.actions;

import frc.robot.subsystems.Intake;

public class SetIntakePositionHatchAction implements Action  
{
    private Intake mIntake = Intake.getInstance();

    private boolean finished = false;
    
    public SetIntakePositionHatchAction()
    {
        finished = false;
    }

    @Override
    public void start()
    {
       mIntake.SetIntakeRotaryHatch();
    }
    
    public void update()
    {
        if (mIntake.mIntakeHatchLimit.get() == false)
        {
            finished = true;
        }    
        else
        {
            finished = false;
        }
    }
    
    public boolean isFinished()
    {
        if (finished) 
        {
			System.out.println("Hatch Position Set");
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    public void done()
    {

    }

}