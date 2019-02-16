package frc.robot.subsystems;
import frc.robot.Setup;
import frc.robot.subsystems.*;

import javax.lang.model.util.ElementScanner6;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;


public class Elevator extends Subsystem {

	static Elevator mInstance=new Elevator();
	DigitalInput mElevatorTopProxHardware;
	DigitalInput mElevatorBottomProxHardware;
	LIDAR mElevatorLaser;
	Setup mSetup = new Setup();

    //Elevator Positions
    double elevatorHighPosition = 0;
	double elevatorMiddlePosition = 0;
	double elevatorLowPosition = 0;
	
	TalonSRX mSpool = new TalonSRX(Setup.kSpoolId);
	VictorSPX mSpool1 = new VictorSPX(Setup.kSpoolId1);
    
    public Elevator() 
{
    mElevatorBottomProxHardware = new DigitalInput(Setup.kElevatorBottomProx);
	mElevatorTopProxHardware = new DigitalInput(Setup.kElevatorTopProx);
	mElevatorLaser = new LIDAR(new DigitalInput(Setup.kElevatorLaser));

}

    public static Elevator getInstance() {
        return mInstance;
    }

    public void setElevatorSpeed(double speed)

	 {
		 if((mElevatorBottomProxHardware.get() == false) && (mElevatorTopProxHardware.get() == false))
		 {
			System.out.println("Elevator Speed" + speed);
			mSpool.set(ControlMode.PercentOutput,speed);
			mSpool1.set(ControlMode.PercentOutput,speed);
		 }
		 else if((mElevatorBottomProxHardware.get() == true) && (mSetup.getSecondaryElevatorAnalog() > 0))
		 {
			mSpool.set(ControlMode.PercentOutput,speed);
			mSpool1.set(ControlMode.PercentOutput,speed);
		 }
		 else if((mElevatorTopProxHardware.get() == false) && (mSetup.getSecondaryElevatorAnalog() < 0))
		 {
			mSpool.set(ControlMode.PercentOutput,speed);
			mSpool1.set(ControlMode.PercentOutput,speed);
		 }
		 else 
		 {
			mSpool.set(ControlMode.PercentOutput,0);
			mSpool1.set(ControlMode.PercentOutput,0);
		 }
     	
     }

    public void setElevatorPosition(String position)
	{
		double ElevatorDistance = mElevatorLaser.getDistance();
			
		if (position == "HIGH")
		{
			while (ElevatorDistance < elevatorHighPosition)
			{
				setElevatorSpeed(.25);
			}
			mSpool.set(ControlMode.PercentOutput,0);
			mSpool1.set(ControlMode.PercentOutput,0);
		} 

		if (position == "MIDDLE")
		{
			while (ElevatorDistance < elevatorMiddlePosition)
			{
				setElevatorSpeed(.25);
			}
			mSpool.set(ControlMode.PercentOutput,0);
			mSpool1.set(ControlMode.PercentOutput,0);

			while (ElevatorDistance > elevatorMiddlePosition)
			{
				setElevatorSpeed(-.25);
			}
			mSpool.set(ControlMode.PercentOutput,0);
			mSpool1.set(ControlMode.PercentOutput,0);
		} 

		if (position == "LOW")
		{
			while (ElevatorDistance > elevatorLowPosition)
			{
				setElevatorSpeed(-.25);
			}
			mSpool.set(ControlMode.PercentOutput,0);
			mSpool1.set(ControlMode.PercentOutput,0);
		}
		} 


    @Override
	public void stop()
	{
		setElevatorSpeed(0);
		System.out.println("Stopping Elevator");

    }
    

	@Override
	public void updateSubsystem(){
		
		double ElevatorDistance = mElevatorLaser.getDistance();

		//Limits
        if (mElevatorBottomProxHardware.get() == true)
        {
			mSpool.set(ControlMode.PercentOutput,0);
			mSpool1.set(ControlMode.PercentOutput,0);
		} 
		
		if (mElevatorTopProxHardware.get() == true)
		{
			mSpool.set(ControlMode.PercentOutput,0);
			mSpool1.set(ControlMode.PercentOutput,0);
		}

		System.out.println("Elevator Laser Distance" + ElevatorDistance);
		//Update Laser
	
		outputToSmartDashboard();
	}
	
	@Override
	public void outputToSmartDashboard() {
		
	}

}