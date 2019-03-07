package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;


public class LED extends Subsystem
{

    static LED mInstance = new LED();

    public static LED getInstance() {
        return mInstance;
    }

    DigitalOutput mLEDPort7;
    DigitalOutput mLEDPort8;
    DigitalOutput mLEDPort9;

    public LED()
    {
        mLEDPort7 = new DigitalOutput(7);
        mLEDPort8 = new DigitalOutput(8);
        mLEDPort9 = new DigitalOutput(9);
    }   

    @Override
    public void updateSubsystem()
    {

    }
    @Override
    public void outputToSmartDashboard()
    {
        
    }
    @Override
    public void stop()
    {

    }

    public void Clear()
    {
        //000
        mLEDPort7.set(true);
        mLEDPort8.set(true);
        mLEDPort9.set(true);
    }
    public void Rainbow()
    {
        //001
        mLEDPort7.set(true);
        mLEDPort8.set(true);
        mLEDPort9.set(false);
    }

    public void Cargo()
    {
        //010
        mLEDPort7.set(true);
        mLEDPort8.set(false);
        mLEDPort9.set(true);
    }

    public void RedFlashyThing()
    {
        //011
        mLEDPort7.set(true);
        mLEDPort8.set(false);
        mLEDPort9.set(false);
    }
    public void TeamBlue()
    {
        //100
        mLEDPort7.set(false);
        mLEDPort8.set(true);
        mLEDPort9.set(true);
    }
    public void TeamRed()
    {
        //101
        mLEDPort7.set(false);
        mLEDPort8.set(true);
        mLEDPort9.set(false);
    }
    public void Hatch()
    {
        //110
        mLEDPort7.set(false);
        mLEDPort8.set(false);
        mLEDPort9.set(true);
    }
    public void ElevatorMaxHeighty()
    {
        //111
        mLEDPort7.set(false);
        mLEDPort8.set(false);
        mLEDPort9.set(false);
    }


}