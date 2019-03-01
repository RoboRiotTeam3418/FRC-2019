package frc.robot.auto.actions;

import frc.robot.subsystems.Elevator;

public class SetElevatorPositionAction implements Action  
{
    private Elevator mElevator = Elevator.getInstance();

    String mType;
    String mPosition;
    private boolean finished = false;
    
    public SetElevatorPositionAction(String type,String position)
    {
        mType = type;
        mPosition = position;
        finished = false;
    }

    @Override
    public void start()
    {
       mElevator.setElevatorPosition(mType, mPosition);
       finished = true;
    }
    
    public void update()
    {
        
    }
    
    public boolean isFinished()
    {
        if (finished) 
        {
			System.out.println("Position Set");
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