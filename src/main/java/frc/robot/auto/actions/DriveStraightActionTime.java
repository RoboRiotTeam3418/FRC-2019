package frc.robot.auto.actions;

import frc.robot.Setup;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;


public class DriveStraightActionTime implements Action {

	
	private Drivetrain mDrivetrain = Drivetrain.getInstance();

	private ADXRS450_Gyro mGyro = Setup.getInstance().mGyro;
    private double mAngleSetpoint = mGyro.getAngle();

	private double mLinearSpeed = .7;
	private double mRotationalMaxSpeed = .5;
	private double mRotationalMinSpeed = .03;
	private boolean mIsForward;
	private double mTimeToWait;
	private double mStartTime;
	private double mCurrentTime;
	private double mAngleCorrectionSpeed;

    public DriveStraightActionTime(double time, boolean isForward) {

        mIsForward = isForward;
        mTimeToWait = time;

    }

    public DriveStraightActionTime(double time, boolean isForward, double LinearSpeed) {

        mIsForward = isForward;
        mTimeToWait = time;
        mLinearSpeed = LinearSpeed;

    }

    

    public DriveStraightActionTime(double feetPerSecond, double distance, boolean isForward) {

        mIsForward = isForward;
        mTimeToWait = feetPerSecond / distance;

    }

    

    public DriveStraightActionTime(double feetPerSecond, double distance, boolean isForward, double LinearSpeed) {

        mIsForward = isForward;
        mTimeToWait = feetPerSecond / distance;
        mLinearSpeed = LinearSpeed;

    }

    

    @Override

	public void start() {

		mDrivetrain.lowGear();
		mStartTime = Timer.getFPGATimestamp();

	}

    

    @Override

	public void update() {

    	calcGyroSpeed();    

    	mCurrentTime = Timer.getFPGATimestamp();

    	if (mIsForward) {

    		mDrivetrain.setTankDriveSpeed(mLinearSpeed + mAngleCorrectionSpeed, mLinearSpeed + -mAngleCorrectionSpeed);

    	} else {

    		mDrivetrain.setTankDriveSpeed(-mLinearSpeed + mAngleCorrectionSpeed, -mLinearSpeed + -mAngleCorrectionSpeed);

    	}

	}

    

    @Override

	public boolean isFinished() {

		if ((mCurrentTime - mStartTime) > mTimeToWait) {

			return true;

		}

		return false;

	}

    

	@Override

	public void done() {

		mDrivetrain.setTankDriveSpeed(0, 0);

		//mDrivetrain.resetEncoders();

		System.out.println("finished with drive straight (timed) action");

	}

	

	 private double calcGyroError() {

	 	return mAngleSetpoint - mGyro.getAngle();

	 }

	

	private void calcGyroSpeed() {

		mAngleCorrectionSpeed = calcGyroError() * .05;

	 	if (mAngleCorrectionSpeed < mRotationalMinSpeed && mAngleCorrectionSpeed > 0 ) {

	 		mAngleCorrectionSpeed = mRotationalMinSpeed;

	 	} else if (mAngleCorrectionSpeed > -mRotationalMinSpeed && mAngleCorrectionSpeed < 0 ) {

	 		mAngleCorrectionSpeed = -mRotationalMinSpeed;

	 	}

		

	 	if (mAngleCorrectionSpeed > mRotationalMaxSpeed ) {

	 		mAngleCorrectionSpeed = mRotationalMaxSpeed;

	 	} else if (mAngleCorrectionSpeed < -mRotationalMaxSpeed ) {

	 		mAngleCorrectionSpeed = -mRotationalMaxSpeed;

	 	}

	 }

}