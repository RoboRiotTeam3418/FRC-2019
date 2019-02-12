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
		mDrivetrain.stop();
		mDrivetrain.lowGear();
		mIntake.stop();
    mElevator.stop();
    mLED.Idle();
	}


  @Override
  public void robotInit() {

    mSetup = Setup.getInstance();
    mDrivetrain = Drivetrain.getInstance();
    mIntake = Intake.getInstance();
    mLED = LED.getInstance();
    stopAllSubsystems();
  
  }

  @Override
	public void autonomousInit() {

		stopAllSubsystems();
		updateAllSubsystems();
		
	}
	
	@Override
	public void autonomousPeriodic() {

		updateAllSubsystems();
	}

  
  @Override
	public void disabledInit(){
		
      mDrivetrain.highGear();
  
		  stopAllSubsystems();
		  updateAllSubsystems();
  }
  
  @Override
	public void disabledPeriodic() {
	 //LED Lights
   if(mSetup.GetLEDRainbowButton()){
    mLED.Rainbow();
  }
  if(mSetup.GetLEDCargoButton()){
   mLED.Rainbow();
 }
 if(mSetup.GetLEDFireButton()){
   mLED.Rainbow();
 }
 if(mSetup.GetLEDTeamBlueButton()){
   mLED.Rainbow();
 }
 if(mSetup.GetLEDTeamRedButton()){
   mLED.Rainbow();
 }
 if(mSetup.GetLEDHatchButton()){
   mLED.Rainbow();
 }
 if(mSetup.GetLEDIdleButton()){
   mLED.Rainbow();
 }
 if(mSetup.GetLEDWarningButton()){
   mLED.Rainbow();
 }

  }

  @Override
	public void teleopInit(){
	 
		stopAllSubsystems();
		mDrivetrain.lowGear();
    updateAllSubsystems();
    
  }
  

  @Override
  public void teleopPeriodic() {
    
    //Controls

    //Intake
		if(mSetup.getSecondaryIntakeButton()) {
      mIntake.intake();
     } 
      else if(mSetup.getSecondaryOutakeButton())
    { 
			mIntake.reverse();
		}
		else {
			mIntake.stopIntakeMotor();
		}
		
		//Drive train 
		if(mSetup.getDriverHighGearButton()) {
			mDrivetrain.highGear();
		}
		if(mSetup.getDriverLowGearButton()) {
			mDrivetrain.lowGear();
		}
		mDrivetrain.setTankDriveSpeed(mSetup.getDriverLeftY(), mSetup.getDriverRightY());

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
     if(mSetup.GetLEDRainbowButton()){
       mLED.Rainbow();
     }
     if(mSetup.GetLEDCargoButton()){
      mLED.Rainbow();
    }
    if(mSetup.GetLEDFireButton()){
      mLED.Rainbow();
    }
    if(mSetup.GetLEDTeamBlueButton()){
      mLED.Rainbow();
    }
    if(mSetup.GetLEDTeamRedButton()){
      mLED.Rainbow();
    }
    if(mSetup.GetLEDHatchButton()){
      mLED.Rainbow();
    }
    if(mSetup.GetLEDIdleButton()){
      mLED.Rainbow();
    }
    if(mSetup.GetLEDWarningButton()){
      mLED.Rainbow();
    }

    updateAllSubsystems();

  }
 



}
