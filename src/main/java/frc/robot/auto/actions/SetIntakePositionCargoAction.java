package frc.robot.auto.actions;

import frc.robot.subsystems.Intake;

public class SetIntakePositionCargoAction implements Action  
{
    private Intake mIntake = Intake.getInstance();

    private boolean finished = false;
    
    public SetIntakePositionCargoAction()
    {
        finished = false;
    }

    @Override
    public void start()
    {
       mIntake.SetIntakeRotaryCargo();
    }
    
    public void update()
    {
        if (mIntake.mIntakeCargoLimit.get() == false)
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
			System.out.println("Cargo Position Set");
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