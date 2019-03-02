/*                                                                                              
      @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
      @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@           @@@@@@@@@@@@@          @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
     @@@@@@@@@@@@@@@            @@@@@@@@@@@@          @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@         @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@         @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@        @@@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@        @@@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@        @@@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@        @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
    @@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@       @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
   @@@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@       @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
   @@@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@       @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
    @@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@      @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
         @@@@@@@@@@@@@@@@@@@@   @@@@@@@@@@@@@@      @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@          
             @@@@@@@@@@@@@@@    @@@@@@@@@@@@@@      @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@              
            @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@      @@@@@@@@@@@@@@     @@@@@@@@@@@@@@@             
           @@@@@@@@@@@@@@@@     @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@      @@@@@@@@@@@@@@@            
           @@@@@@@@@@@@@@@      @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@       @@@@@@@@@@@@@@@           
          @@@@@@@@@@@@@@@       @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@       @@@@@@@@@@@@@@@@          
         @@@@@@@@@@@@@@@        @@@@@@@@@@@@@@@    @@@@@@@@@@@@@@@        @@@@@@@@@@@@@@@          
        @@@@@@@@@@@@@@@@         @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@         @@@@@@@@@@@@@@@         
       @@@@@@@@@@@@@@@@          @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@        
      @@@@@@@@@@@@@@@@           @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@@       
     @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@           @@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@             @@@@@@@@@@@@@@@   @@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@             @@@@@@@@@@@@@@@   @@@@@@@@@@@@@@@             @@@@@@@@@@@@@@@@    
   @@@@@@@@@@@@@@@@              @@@@@@@@@@@@@@@  @@@@@@@@@@@@@@@              @@@@@@@@@@@@@@@@    
  @@@@@@@@@@@@@@@@               @@@@@@@@@@@@@@@  @@@@@@@@@@@@@@@               @@@@@@@@@@@@@@@@   
 @@@@@@@@@@@@@@@@                @@@@@@@@@@@@@@@  @@@@@@@@@@@@@@@                @@@@@@@@@@@@@@@@  
                                                                                                   
*/



package frc.robot;
import frc.robot.auto.modes.DeliverHatchHighPosition;
import frc.robot.auto.modes.DeliverHatchLowPosition;
import frc.robot.auto.modes.DeliverHatchMiddlePosition;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import java.util.Scanner;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.subsystems.LED;
import frc.robot.auto.AutoExecuter;


public class Robot extends IterativeRobot  {

  //Initialize main parts of the robot
  Setup mSetup;
  Drivetrain mDrivetrain;
  Elevator mElevator;
  Intake mIntake;
  LED mLED;

  AutoExecuter mAutoExecuter = null;
  
  public void updateAllSubsystems(){
		
		mDrivetrain.updateSubsystem();
	  mIntake.updateSubsystem();
    mElevator.updateSubsystem();
    mLED.updateSubsystem();
  }
  
  public void stopAllSubsystems(){
    //System.out.println("Robot Stopping");
		mDrivetrain.stop();
		mDrivetrain.lowGear();
		mIntake.stop();
    mElevator.stop();
    mLED.Clear();
  }
  
 public void auto(String automode)
 {
    if (mAutoExecuter != null) {

      mAutoExecuter.stop();

    }
    mAutoExecuter = new AutoExecuter();

   switch(automode) {

     case "DeliverHatchHighPosition":
      mAutoExecuter.setAutoRoutine(new DeliverHatchHighPosition());
      mAutoExecuter.start();
 			break;
     case "DeliverHatchMiddlePosition":
      mAutoExecuter.setAutoRoutine(new DeliverHatchMiddlePosition());
      mAutoExecuter.start();
 		  break;
     case "DeliverHatchLowPosition":
     mAutoExecuter.setAutoRoutine(new DeliverHatchLowPosition());
     mAutoExecuter.start();
       break;
     default:
 			break;
 		}
}
public void manual()
{
    
    //Controls

    //Drive train 
		if(mSetup.getDriverHighGearButton()) {
			mDrivetrain.highGear();
		}
		if(mSetup.getDriverLowGearButton()) {
			mDrivetrain.lowGear();
    }
    
    // if (mSetup.getDriverSlowSpeed())
    // {
    //   mDrivetrain.setTankDriveSpeed(mSetup.getDriverLeftY()*.5, mSetup.getDriverRightY()*.5);  
    // }
    // else
    // {
    //   mDrivetrain.setTankDriveSpeed(mSetup.getDriverLeftY(), mSetup.getDriverRightY());
    // }
    
    /*if (mSetup.getDriverAccuracy()){
      
      if (mSetup.getDriverSlowSpeed()){
        mDrivetrain.setTankDriveSpeed(mSetup.getDriverLeftX()*.25, mSetup.getDriverLeftX() * -.25);
        mDrivetrain.setTankDriveSpeed(mSetup.getDriverLeftY()*.25, mSetup.getDriverLeftY() * -.25);
      }
    
      mDrivetrain.setTankDriveSpeed(mSetup.getDriverLeftY()*.25, mSetup.getDriverLeftY() * .25);
      
    }*/

    if(mSetup.getDriverPov() == 0 || mSetup.getDriverPov() == 45 || mSetup.getDriverPov() == 315){
      mDrivetrain.setTankDriveSpeed(-.25, -.25);
    }
    else if(mSetup.getDriverPov() == 180 || mSetup.getDriverPov() == 225 || mSetup.getDriverPov() == 135){
      mDrivetrain.setTankDriveSpeed(.25, .25);
    }
    else if(mSetup.getDriverPov() == 90 || mSetup.getDriverPov() == 45 || mSetup.getDriverPov() == 135){
      mDrivetrain.setTankDriveSpeed(.25, -.25);
    }
    else if(mSetup.getDriverPov() == 270 || mSetup.getDriverPov() == 225 || mSetup.getDriverPov() == 315){
      mDrivetrain.setTankDriveSpeed(-.25, .25);
    }
    else
    {
      mDrivetrain.setTankDriveSpeed(mSetup.getDriverRightY(), mSetup.getDriverLeftY());
    }


    //Cargo Intake
		if(mSetup.getSecondaryCargoIntakeButton()) {
      mIntake.intakeCargo();
     } 
   else if(mSetup.getSecondaryCargoOuttakeButton())
    { 
			mIntake.outtakeCargo();
		}
		else {
			mIntake.stopCargoIntakeMotor();
    }
    
     //Hatch Intake
		if(mSetup.getMrHuckSuckButton()) {
      mIntake.IntakeHatch();
     } 
    else if(mSetup.getMrHuckStopButton())
    { 
			mIntake.stopSucking();
		}
	
    //Intake Rotary
    if(mSetup.getSecondaryIntakeRotaryCargoButton()) {
      mIntake.SetIntakeRotaryCargoState();
     } 

     if(mSetup.getSecondaryIntakeRotaryHatchButton()) {
      mIntake.SetIntakeRotaryHatchState();
     } 

		//Elevator Analog
		mElevator.setElevatorSpeedAnalog(mSetup.getSecondaryElevatorAnalog());

    //Elevator Positions
    if(mSetup.getSecondaryElevatorHighButton()) {
      mElevator.setElevatorPosition("HATCH", "HIGH");
     } 

    if(mSetup.getSecondaryElevatorMiddleButton()) {
      mElevator.setElevatorPosition("HATCH","MIDDLE");
     } 

    if(mSetup.getSecondaryElevatorLowButton()) {
      mElevator.setElevatorPosition("HATCH","LOW");
     } 


     //Elevator Auto Deliver
    //  if(mSetup.getSecondaryElevatorHighButton()) {
    //   mElevator.setElevatorPosition("HATCH", "HIGH");
    //  } 

    // if(mSetup.getSecondaryElevatorMiddleButton()) {
    //   mElevator.setElevatorPosition("HATCH","MIDDLE");
    //  } 

    // if(mSetup.getSecondaryElevatorLowButton()) {
    //   mElevator.setElevatorPosition("HATCH","LOW");
    //  } 


     //LED Lights
     if(mSetup.GetLEDClearButton()){
      mLED.Clear();
    }
     if(mSetup.GetLEDRainbowButton()){
       mLED.Rainbow();
     }
     if(mSetup.GetLEDCargoButton()){
      mLED.Cargo();
    }
    if(mSetup.GetLEDRedFlashyThingButton()){
      mLED.RedFlashyThing();
    }
    if(mSetup.GetLEDTeamBlueButton()){
      mLED.TeamBlue();
    }
    if(mSetup.GetLEDTeamRedButton()){
      mLED.TeamRed();
    }
    if(mSetup.GetLEDHatchButton()){
      mLED.Hatch();
    }
    if(mSetup.GetLEDElevatorMaxHeightyButton()){
      mLED.ElevatorMaxHeighty();
    }
    if(mSetup.GetLEDNoButton()){
      mLED.Clear();
    }

    updateAllSubsystems();

}



  @Override
  public void robotInit() {
 
    mSetup = Setup.getInstance();
    mDrivetrain = Drivetrain.getInstance();
    mIntake = Intake.getInstance();
    mLED = LED.getInstance();
    mElevator = Elevator.getInstance();
    System.out.println("Robot Init");
    stopAllSubsystems();
  
  }

  @Override
	public void autonomousInit() {
    System.out.println("Auto Init");
		stopAllSubsystems();
		updateAllSubsystems();
		
	}
	
	@Override
	public void autonomousPeriodic() {
    manual();
		updateAllSubsystems();
	}

  
  @Override
	public void disabledInit(){
      System.out.println("Disabled Init");
      mDrivetrain.highGear();
		  stopAllSubsystems();
		  updateAllSubsystems();
  }
  
  @Override
	public void disabledPeriodic() {

  System.out.println("Disabled Periodic");

	 //LED Lights
   if(mSetup.GetLEDClearButton()){
    mLED.Clear();
  }
   if(mSetup.GetLEDRainbowButton()){
     mLED.Rainbow();
   }
   if(mSetup.GetLEDCargoButton()){
    mLED.Cargo();
  }
  if(mSetup.GetLEDRedFlashyThingButton()){
    mLED.RedFlashyThing();
  }
  if(mSetup.GetLEDTeamBlueButton()){
    mLED.TeamBlue();
  }
  if(mSetup.GetLEDTeamRedButton()){
    mLED.TeamRed();
  }
  if(mSetup.GetLEDHatchButton()){
    mLED.Hatch();
  }
  if(mSetup.GetLEDElevatorMaxHeightyButton()){
    mLED.ElevatorMaxHeighty();
  }
  if(mSetup.GetLEDNoButton()){
    mLED.Clear();
  }
  }

  @Override
	public void teleopInit(){
		stopAllSubsystems();
    mDrivetrain.lowGear();
    System.out.println("Tele Init");
    updateAllSubsystems();
    
  }
  
  @Override
  public void teleopPeriodic() {
    
    if (mSetup.getSecondaryAutoStopButton())
    {
      mAutoExecuter.stop();
    }

    if(mSetup.getSecondaryElevatorMiddleButton())
    {
      auto("DeliverHatchMiddlePosition");
    }
    else
    {
      manual();
    }

  }
 
}
