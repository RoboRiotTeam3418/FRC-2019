package frc.robot.subsystems;
import frc.robot.Setup;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Intake extends Subsystem
{

    static Intake mInstance = new Intake();

    public static Intake getInstance() {
    	return mInstance;
    }

	private VictorSPX mIntake;
	private TalonSRX mIntakeRotary;

	/* Nonzero to block the config until success, zero to skip checking */
    final int kTimeoutMs = 30;
	
    /**
	 * If the measured travel has a discontinuity, Note the extremities or
	 * "book ends" of the travel.
	 */
	final boolean kDiscontinuityPresent = true;
	final int kBookEnd_0 = 910;		/* 80 deg */
	final int kBookEnd_1 = 1137;	/* 100 deg */

	

	

public Intake() {
			
		mIntake= new VictorSPX(Setup.kIntakeId);
		mIntake.setInverted(false);
		System.out.println("Intake Done Initializing.");
		mIntake.set(ControlMode.PercentOutput, 0);

		mIntakeRotary = new TalonSRX(Setup.kIntakeRotaryId);

		/* Factory Default Hardware to prevent unexpected behaviour */
		mIntakeRotary.configFactoryDefault();

		/* Seed quadrature to be absolute and continuous */
		initQuadrature();
		
		/* Configure Selected Sensor for Talon */
		mIntakeRotary.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,	// Feedback
										0, 											// PID ID
										kTimeoutMs);								// Timeout

		
    }
    
    public enum IntakeRollerState {
    	INTAKE,
    	REVERSE,
    	STOP;
    }

     private IntakeRollerState mIntakeRollerState;
//intakerollerstate
    @Override
	public void updateSubsystem()
	{
		switch(mIntakeRollerState) {

		case INTAKE:
			setRollerSpeed(Setup.kIntakeSpeed);
			break;
		case REVERSE:
			setRollerSpeed(Setup.kIntakeReverseSpeed);
			break;
		case STOP:
			setRollerSpeed(0);
			break;
		default:
			mIntakeRollerState = IntakeRollerState.STOP;
			break;
		}
		
		
		outputToSmartDashboard();
	}	
	
	
	public void intake(){
		mIntakeRollerState = IntakeRollerState.INTAKE;
	}
	
	public void reverse(){
		mIntakeRollerState = IntakeRollerState.REVERSE;
	}
	
	public void intakePosition(String position)
	{
	
		if (position == "HATCH")
		{
			
		} 

		if (position == "CARGO")
		{
			
		}


		} 
	}
	
	@Override
	public void stop(){
		mIntakeRollerState = IntakeRollerState.STOP;
		System.out.println("Stopping Intake");
	}
	public void stopIntakeMotor()
	{
		mIntakeRollerState = IntakeRollerState.STOP;
	}

	public void setRollerSpeed(double speed) {

		mIntake.set(ControlMode.PercentOutput, speed);
		
	}
	
	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putString("Intake_Roller_State", mIntakeRollerState.toString());
		
	}

	public void initQuadrature() {
		/* get the absolute pulse width position */
		int pulseWidth = mIntakeRotary.getSensorCollection().getPulseWidthPosition();

		/**
		 * If there is a discontinuity in our measured range, subtract one half
		 * rotation to remove it
		 */
		if (kDiscontinuityPresent) {

			/* Calculate the center */
			int newCenter;
			newCenter = (kBookEnd_0 + kBookEnd_1) / 2;
			newCenter &= 0xFFF;

			/**
			 * Apply the offset so the discontinuity is in the unused portion of
			 * the sensor
			 */
			pulseWidth -= newCenter;
		}

		/**
		 * Mask out the bottom 12 bits to normalize to [0,4095],
		 * or in other words, to stay within [0,360) degrees 
		 */
		pulseWidth = pulseWidth & 0xFFF;

		/* Update Quadrature position */
		mIntakeRotary.getSensorCollection().setQuadraturePosition(pulseWidth, kTimeoutMs);
	}


	String ToDeg(int units) {
		double deg = units * 360.0 / 4096.0;

		/* truncate to 0.1 res */
		deg *= 10;
		deg = (int) deg;
		deg /= 10;

		return "" + deg;
	}

	public void disabledPeriodic() {
		/**
		 * When button is pressed, seed the quadrature register. You can do this
		 * once on boot or during teleop/auton init. If you power cycle the 
		 * Talon, press the button to confirm it's position is restored.
		 */
		if (_joy.getRawButton(1)) {
			initQuadrature();
		}

		/**
		 * Quadrature is selected for soft-lim/closed-loop/etc. initQuadrature()
		 * will initialize quad to become absolute by using PWD
		 */
		int selSenPos = _talon.getSelectedSensorPosition(0);
		int pulseWidthWithoutOverflows = 
				_talon.getSensorCollection().getPulseWidthPosition() & 0xFFF;

		/**
		 * Display how we've adjusted PWM to produce a QUAD signal that is
		 * absolute and continuous. Show in sensor units and in rotation
		 * degrees.
		 */
		System.out.print("pulseWidPos:" + pulseWidthWithoutOverflows +
						 "   =>    " + "selSenPos:" + selSenPos);
		System.out.print("      ");
		System.out.print("pulseWidDeg:" + ToDeg(pulseWidthWithoutOverflows) +
						 "   =>    " + "selSenDeg:" + ToDeg(selSenPos));
		System.out.println();
	}




}