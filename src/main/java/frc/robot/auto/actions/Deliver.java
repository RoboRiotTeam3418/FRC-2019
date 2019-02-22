package frc.robot.auto.actions;
frc.robot.subsystems.Elevator;public class Deliver implements Action  
{
    String mType;
    String mPosition;



    public void DeliverAction (String type,String position)
    {
        mType = type;
        mPosition = position;

        
    }
    @Override
    public void start()
    {
       

    }
    
    public void update()
    {

    }
    
    public boolean isFinished()
    {
        
    }
    
    public void done()
    {

    }

}