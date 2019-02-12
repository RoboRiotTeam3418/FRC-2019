package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
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

    public void Idle()
    {
        //000
        mLEDPort7.set(false);
        mLEDPort8.set(false);
        mLEDPort9.set(false);
    }
    public void Rainbow()
    {
        //001
        mLEDPort7.set(false);
        mLEDPort8.set(false);
        mLEDPort9.set(true);
    }

    public void Intake()
    {
        //010
        mLEDPort7.set(false);
        mLEDPort8.set(true);
        mLEDPort9.set(false);
    }

    public void Fire()
    {
        //011
        mLEDPort7.set(false);
        mLEDPort8.set(true);
        mLEDPort9.set(true);
    }
    public void TeamBlue()
    {
        //100
        mLEDPort7.set(true);
        mLEDPort8.set(false);
        mLEDPort9.set(false);
    }
    public void TeamRed()
    {
        //101
        mLEDPort7.set(true);
        mLEDPort8.set(false);
        mLEDPort9.set(true);
    }
    public void Hatch()
    {
        //110
        mLEDPort7.set(true);
        mLEDPort8.set(true);
        mLEDPort9.set(false);
    }
    public void IdleThing()
    {
        //111
        mLEDPort7.set(true);
        mLEDPort8.set(true);
        mLEDPort9.set(true);
    }

}