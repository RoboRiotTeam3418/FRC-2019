package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
//import edu.wpi.first.wpilibj.Joystick; 
//never used
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Setup
{

    private static Setup mInstance = new Setup();

    public static Setup getInstance() {
        return mInstance;
    }
    

//----------------------------------------------------------------------------Controls-----------------------------------------------------------------------------------------//
    //Creates Joystick Object
    Joystick mDriverStick;
    Joystick mSecondaryDriverStick;
    Joystick mSwitchboard;

    //Initialize Joystick Object
    void ControlBoard() {
    	mDriverStick = new Joystick(0);
        mSecondaryDriverStick = new Joystick(1);
        mSwitchboard = new Joystick(2);
    }

     //DRIVER CONTROLLER
    
    //Drive Controls
    public double getDriverLeftX(){
    	return mDriverStick.getRawAxis(0);
    }
    
    public double getDriverLeftY(){
    	return -mDriverStick.getRawAxis(1);
    }
    
    public double getDriverRightX(){
    	return mDriverStick.getRawAxis(4);
    }
    
    public double getDriverRightY(){
    	return -mDriverStick.getRawAxis(5);
    }
    
    public int getDriverPov(){
    	return mDriverStick.getPOV(0);
    }
    
    //Driver Gear Controls
    public boolean getDriverHighGearButton(){
    	return mDriverStick.getRawButton(6);
    }
    
    public boolean getDriverLowGearButton(){
        return mDriverStick.getRawButton(5);
    }


    //SECONDARY CONTROLLER


    //Secondary Functional Controls

    //Intake Motors
    public boolean getSecondaryIntakeButton(){
    	return mSecondaryDriverStick.getRawAxis(1) > .2;
    }
    
    public boolean getSecondaryOutakeButton(){
    	return mSecondaryDriverStick.getRawAxis(1) < -.2;
    }
    
    //Mr Hush
    public boolean getMrHushSuck(){
    	return mSecondaryDriverStick.getRawButton(5);
    }
    
    public boolean getMrHushPush(){
    	return mSecondaryDriverStick.getRawButton(6);
    }
    

    //Elevator 
    public double getSecondaryElevatorAnalog(){
    	return mSecondaryDriverStick.getRawAxis(5);
	}

	public boolean getSecondaryElevatorHighButton(){
    	return mSecondaryDriverStick.getRawButton(4);
	}
	
	public boolean getSecondaryElevatorMiddleButton(){
    	return mSecondaryDriverStick.getRawButton(2);
	}
	
	public boolean getSecondaryElevatorLowButton(){
    	return mSecondaryDriverStick.getRawButton(1);
    }

    //Switch Board
    public boolean GetLEDWarningButton(){
    	return mSwitchboard.getRawButton(1);
    }
    public boolean GetLEDRainbowButton(){
    	return mSwitchboard.getRawButton(2);
    }
    public boolean GetLEDCargoButton(){
    	return mSwitchboard.getRawButton(3);
    }
    public boolean GetLEDFireButton(){
    	return mSwitchboard.getRawButton(4);
    }
    public boolean GetLEDTeamBlueButton(){
    	return mSwitchboard.getRawButton(5);
    }
    public boolean GetLEDTeamRedButton(){
    	return mSwitchboard.getRawButton(6);
    }
    public boolean GetLEDHatchButton(){
    	return mSwitchboard.getRawButton(7);
    }
    public boolean GetLEDIdleButton(){
    	return mSwitchboard.getRawButton(8);
    }
   
//----------------------------------------------------------------------------Hardware Map------------------------------------------------------------------------------------//

//Speed Controllers
public VictorSPX mIntakeHardware;
public VictorSPX mMrHushHardware;

public TalonSRX mSpoolHardware;
public TalonSRX mIntakeRotaryHardware;

//Pneumatics
public Compressor mCompressorHardware;
public Solenoid mLeftShifterHardware;
public Solenoid mRightShifterHardware;

//Sensos
//public ADXRS450_Gyro mGyro;	
public DigitalInput mElevatorTopProxHardware;
public DigitalInput mElevatorBottomProxHardware;

void HardwareMap() {
    //test
    try
    {
        //Speed Controllers
        mIntakeHardware = new VictorSPX(Setup.kIntakeId);
        mMrHushHardware = new VictorSPX(Setup.kMrHushId);
        
        mSpoolHardware = new TalonSRX(Setup.kSpoolId);
        mIntakeRotaryHardware = new TalonSRX(Setup.kIntakeRotaryId);

        //Pneumatics
        mLeftShifterHardware = new Solenoid(Setup.kShifterSolenoidId);
        mCompressorHardware = new Compressor(0);

        //Sensors
        //mGyro = new ADXRS450_Gyro();
        //mGyro.calibrate();

    }
    catch(Exception e)
    {
        
    }
}

//-----------------------------------------------------------------------------------Constants----------------------------------------------------------------------------------//


//-subsystem speed motor speeds--//

//Intake
public static double kIntakeSpeed = 1;
public static double kIntakeReverseSpeed = -1;
public static double kSpoolSpeed = .25;


//-static port assignments-//

//CAN

//Drive Train
public static int kLeftFrontMotorId = 0;
public static int kLeftRearMotorId = 1;
public static int kRightFrontMotorId = 2;
public static int kRightRearMotorId = 3;

//Intake
public static int kIntakeId = 5;
public static int kIntakeRotaryId = 6;
public static int kMrHushId = 7;


//Elevator
public static int kSpoolId = 8;

//SOLENOIDS (0-7)


//Shifters
public static int kShifterSolenoidId = 0;


//ANALOG (0-4) 

//Lasers
public static int kElevatorLaserId= 0;
public static int kLeftAllignLaserId = 1;
public static int kRightAllignLaserId = 2;



//DIO
public static int kElevatorBottomProx = 0;
public static int kElevatorTopProx = 1;

}