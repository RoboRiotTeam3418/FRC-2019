package frc.robot.subsystems;

//Not sure if this is right??
import robot.Setup;
// 


import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import java.util.Scanner;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import java.lang.Math;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;


public class Drivetrain extends Subsystem {

    static Drivetrain mInstance = new Drivetrain();

    public static Drivetrain getInstance() {
        return mInstance;
    }


    //Shifters
    Solenoid mLeftSolenoid;
    Solenoid mRightSolenoid;
   
    //Drive Motors
    CANSparkMax mRightFrontDrive;
    CANSparkMax mRightRearDrive;
    CANSparkMax mLeftFrontDrive;
    CANSparkMax mLeftRearDrive;

    public Drivetrain(){
    
    	mLeftSolenoid = Setup.getInstance().mLeftShifterHardware;
    	mRightSolenoid = Setup.getInstance().mRightShifterHardware;
        
    	mLeftFrontDrive = new CANSparkMax(Setup.kLeftFrontMotorId, MotorType.kBrushless);
    	mLeftFrontDrive.set(0);
    	mLeftFrontDrive.setInverted(false);
    	
        mRightFrontDrive = new CANSparkMax(Setup.kRightFrontMotorId, MotorType.kBrushless);
    	mRightFrontDrive.set(0);
    	mRightFrontDrive.setSensorPhase(false);
    	mRightFrontDrive.setInverted(true);
    	
    	mLeftRearDrive = new CANSparkMax(Setup.kLeftRearMotorId, MotorType.kBrushless);
    	mLeftRearDrive.set(0);
    	mLeftRearDrive.setInverted(false);
    	
    	mRightRearDrive = new CANSparkMax(Setup.kRightRearMotorId, MotorType.kBrushless);
    	mRightRearDrive.set(0);
    	mRightRearDrive.setInverted(true);
    
		}
    
    private DriveGear mDriveGear;
    private double mLeftSpeed;
    private double mRightSpeed;
    private double mMoveSpeed;
    private double mRotateSpeed;
    
    public enum DriveGear {
    	LOW, HIGH
    }
    
    public DriveGear getDriveGear(){
    	return mDriveGear;
    }
    
    public void resetEncoders(){
    	mLeftEncoder.reset();
    	mRightEncoder.reset();
    }
    
    
    public void setTankDriveSpeed(double left, double right){
    	//System.out.println("Left speed = " + left + " right speed = " + right);
    	mLeftSpeed = -left;
    	mRightSpeed = right;
    	mLeftFrontDrive.set(left);
    	mLeftRearDrive.set(left);
    	mRightFrontDrive.set(right);
    	mRightRearDrive.set(right);
    }
    

    //Stop
    @Override
    public void stop(){
    	mLeftFrontDrive.set(0);
    	mLeftRearDrive.set(0);
    	mRightFrontDrive.set(0);
    	mRightRearDrive.set(0);
    }
    

    //Gears
    private void setLowGear() {
    	
    	mLeftSolenoid.set(false);
    	mRightSolenoid.set(false);
    }
	
    private void setHighGear() {

    	mLeftSolenoid.set(true);
    	mRightSolenoid.set(true);
    }
    
    
    //Update
	@Override
	public void updateSubsystem() {
        
		switch(mDriveGear){
		case HIGH:
			setHighGear();
			break;
		case LOW:
			setLowGear();
			break;
		default:
			highGear();
			break;
		}
		
		outputToSmartDashboard();
		
	}
	
	public void highGear(){
		mDriveGear = DriveGear.HIGH;
	}
	
	public void lowGear(){
		mDriveGear = DriveGear.LOW;
	}

	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("DriveTrain_LeftMotorSpeeds", mLeftSpeed);
		SmartDashboard.putNumber("DriveTrain_RightMotorSpeeds", mRightSpeed);
		SmartDashboard.putNumber("DriveTrain_MoveValue", mMoveSpeed);
		SmartDashboard.putNumber("DriveTrain_RotateValue", mRotateSpeed);
		SmartDashboard.putNumber("Right_Drivetrain_Encoder_Distance", mRightEncoder.getDistance());
		SmartDashboard.putString("Drive_Gear", mDriveGear.toString());
	}


}
