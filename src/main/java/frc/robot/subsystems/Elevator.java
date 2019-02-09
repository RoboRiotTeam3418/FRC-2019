package frc.robot.subsystems;
import frc.robot.Setup;
import frc.robot.subsystems.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;


public class Elevator extends Subsystem {

	static Elevator mInstance;
	DigitalInput mElevatorTopProxHardware;
	DigitalInput mElevatorBottomProxHardware;
	AnalogInput mElevatorLaser;

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
	mElevatorLaser = new AnalogInput(Setup.kElevatorLaserId)

}

    public static Elevator getInstance() {
        return mInstance;
    }

    public void setElevatorSpeed(double speed)

	 {
     	System.out.println("Elevator Speed" + speed);
     	mSpool.set(ControlMode.PercentOutput,speed);

     }

    public void setElevatorPosition(String position)
	{
		double volts = mElevatorLaser.getVoltage()
		double raw = ElevatorLaser.getValue();
		double averageRaw = ElevatorLaser.getAverageValue();
		double averageVolts = ElevatorLaser.getAverageVoltage();
		
		
		if (position == "HIGH")
		{
			while (volts < elevatorHighPosition)
			{
				setElevatorSpeed(.25);
			}
		} 

		if (position == "MIDDLE")
		{
			while (volts < elevatorMiddlePosition)
			{
				setElevatorSpeed(.25);
			}

			while (volts > elevatorMiddlePosition)
			{
				setElevatorSpeed(-.25);
			}
		} 

		if (position == "LOW")
		{
			while (volts > elevatorLowPosition)
			{
				setElevatorSpeed(-.25);
			}
		}
		} 


    @Override
	public void stop()
	{
    	mSpool.set(ControlMode.PercentOutput,0);
    }
    

	@Override
	public void updateSubsystem(){

        //Limits

        if (elevatorBottomProx = 1 && elevatorTopProx = 1)
        {
            setElevatorSpeed(0);
            mSpool.set(ControlMode.PercentOutput,0);
        } 

		System.out.println("Elevator Laser Raw" + raw);
		System.out.println("Elevator Laser Volts" + volts);
		//Update Laser
		double raw = ElevatorLaser.getValue();
		double volts = ElevatorLaser.getVoltage();
		double averageRaw = ElevatorLaser.getAverageValue();
        double averageVolts = ElevatorLaser.getAverageVoltage();
    
		outputToSmartDashboard();
	}
	
	@Override
	public void outputToSmartDashboard() {
		
	}



}