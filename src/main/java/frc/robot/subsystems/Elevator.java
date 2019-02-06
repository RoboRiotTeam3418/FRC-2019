package frc.robot.subsystems;
import frc.robot.Setup;
import frc.robot.subsystems.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;



import edu.wpi.first.wpilibj.DigitalInput;


public class Elevator extends Subsystem {

	static Elevator mInstance;
	DigitalInput mElevatorTopProxHardware;
    DigitalInput mElevatorBottomProxHardware;
    //Elevator Positions
    double elevatorHighPosition = 0;
	double elevatorMiddlePosition = 0;
	double elevatorLowPosition = 0;
	
    boolean elevatorBottomProx = mElevatorBottomProxHardware.get();
    boolean elevatorTopProx = mElevatorTopProxHardware.get();

	TalonSRX mSpool, SpoolHardware = new TalonSRX(Setup.kSpoolId);
    
    public Elevator() 
{
    mElevatorBottomProxHardware = new DigitalInput(Setup.kElevatorBottomProx);
	mElevatorTopProxHardware = new DigitalInput(Setup.kElevatorTopProx);

}

//    mElevatorBottonProxHardware = new DigitalInput(Setup.kElevatorBottomProx);
//    mElevatorTopProxHardware = new DigitalInput(Setup.kElevatorTopProx);


    public static Elevator getInstance() {
        return mInstance;
    }




    // public void setElevatorSpeed(double speed)

	// {
    // 	System.out.println("Elevator Speed" + this.speed);
    // 	mElevatorSpeed = this.speed;
    // 	mSpool.set(ControlMode.PercentOutput,this.speed);

    // }


    public void setElevatorPosition(String position)
	{
		if (position == "HIGH")
		{
			while (volts < elevatorHighPosition)
			{
				setElevatorSpeed(.25, .25);
			}
		} 

		if (position == "MIDDLE")
		{
			while (volts < elevatorMiddlePosition)
			{
				setElevatorSpeed(.25, .25);
			}

			while (volts > elevatorMiddlePosition)
			{
				setElevatorSpeed(-.25, -.25);
			}
		} 

		if (position == "LOW")
		{
			while (volts > elevatorLowPosition)
			{
				//setElevatorSpeed(-.25, -.25);
			}
		} 


        @Override
	public void stop()
	{
    	mLeftElevator.set(ControlMode.PercentOutput,0);
		mRightElevator.set(ControlMode.PercentOutput,0);
		
    }
    

	@Override
	public void updateSubsystem(){

        //Limits

        if (elevatorBottomProx = elevatorTopProx)
        {
            setElevatorSpeed(0);
            mSpool.set(ControlMode.PercentOutput,0);
        } 

		//Update Laser
		raw = ElevatorLaser.getValue();
		volts = ElevatorLaser.getVoltage();
		averageRaw = ElevatorLaser.getAverageValue();
        averageVolts = ElevatorLaser.getAverageVoltage();
    
		outputToSmartDashboard();
	}
	
	@Override
	public void outputToSmartDashboard() {
		
	}



}