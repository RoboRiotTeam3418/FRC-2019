package frc.robot.auto.actions;

import frc.robot.subsystems.Elevator;

public class SetElevatorPositionAction implements Action  
{
    private Elevator mElevator = Elevator.getInstance();

    String mType;
    String mPosition;

    public void DeliverAction (String type,String position)
    {
        mType = type;
        mPosition = position;
        private boolean finished = false;

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
    }
    
    public void done()
    {

    }

}