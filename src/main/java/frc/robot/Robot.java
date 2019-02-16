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
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import java.util.Scanner;

import edu.wpi.first.wpilibj.IterativeRobot;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.subsystems.LED;


public class Robot extends IterativeRobot  {

  //Initialize main parts of the robot
  Setup mSetup;
  Drivetrain mDrivetrain;
  Elevator mElevator;
  Intake mIntake;
  LED mLED;
  
  public void updateAllSubsystems(){
		
		mDrivetrain.updateSubsystem();
	  mIntake.updateSubsystem();
    mElevator.updateSubsystem();
    mLED.updateSubsystem();
  }
  
  public void stopAllSubsystems(){
    System.out.println("Robot Stopping");
		mDrivetrain.stop();
		mDrivetrain.lowGear();
		mIntake.stop();
    mElevator.stop();
    mLED.Clear();
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
System.out.println("Auto Periodic");
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
	 System.out.println("Tele Init");
		stopAllSubsystems();
		mDrivetrain.lowGear();
    updateAllSubsystems();
    
  }
  

  @Override
  public void teleopPeriodic() {
    System.out.println("Tele Periodic");
    //Controls

    //Drive train 
		if(mSetup.getDriverHighGearButton()) {
			mDrivetrain.highGear();
		}
		if(mSetup.getDriverLowGearButton()) {
			mDrivetrain.lowGear();
		}
		mDrivetrain.setTankDriveSpeed(mSetup.getDriverLeftY(), mSetup.getDriverRightY());

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
		else {
			mIntake.stopSucking();
		}
    
    //Intake Rotary
    if(mSetup.getSecondaryIntakeRotaryCargoButton()) {
      mIntake.SetIntakeRotaryCargo();
     } 

     if(mSetup.getSecondaryIntakeRotaryHatchButton()) {
      mIntake.SetIntakeRotaryHatch();
     } 

		//Elevator Analog
		mElevator.setElevatorSpeed(mSetup.getSecondaryElevatorAnalog());


    //Elevator Positions
    if(mSetup.getSecondaryElevatorHighButton()) {
      mElevator.setElevatorPosition("HIGH");
     } 

    if(mSetup.getSecondaryElevatorMiddleButton()) {
      mElevator.setElevatorPosition("MIDDLE");
     } 

    if(mSetup.getSecondaryElevatorLowButton()) {
      mElevator.setElevatorPosition("LOW");
     } 


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
 



}
