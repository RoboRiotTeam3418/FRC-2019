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
import edu.wpi.first.wpilibj.DigitalInput;

public class Intake extends Subsystem
{

    static Intake mInstance = new Intake();

    public static Intake getInstance() {
    	return mInstance;
    }

	private VictorSPX mIntake;
	private TalonSRX mIntakeRotary;
	private VictorSPX mMrHuck;
	private VictorSPX mMrHuckJr;

	public DigitalInput mIntakeCargoLimit;
	public DigitalInput mIntakeHatchLimit;
	
public Intake() {

		mIntake= new VictorSPX(Setup.kIntakeId);
		mIntake.setInverted(false);

		mMrHuck = new VictorSPX(Setup.kMrHuckId);
		mIntake.setInverted(false);

		mMrHuckJr = new VictorSPX(Setup.kMrHuckJrId);
		mIntake.setInverted(false);

		mIntake.set(ControlMode.PercentOutput, 0);
		mIntakeRotary = new TalonSRX(Setup.kIntakeRotaryId);

		mIntakeCargoLimit = new DigitalInput(Setup.kIntakeCargoLimit);
		mIntakeHatchLimit = new DigitalInput(Setup.kIntakeHatchLimit);


		//System.out.println("Intake Done Initializing.");
    }
//-------------------------------------------------------------------------------Intake Cargo----------------------------------------------------------------------------------//

    public enum IntakeCargoState {
    	INTAKE,
    	OUTTAKE,
    	STOP;
    }

	 private IntakeCargoState mIntakeCargoState=IntakeCargoState.STOP;

	 public IntakeCargoState getIntakeCargoState() {
		return mIntakeCargoState;
	}
	 
	public void intakeCargo(){
		mIntakeCargoState = IntakeCargoState.INTAKE;
	}

	public void outtakeCargo(){
		mIntakeCargoState = IntakeCargoState.OUTTAKE;
	}

	public void stopCargoIntakeMotor(){
		mIntakeCargoState = IntakeCargoState.STOP;
	}

	public void setCargoIntakeSpeed(double speed) {
		mIntake.set(ControlMode.PercentOutput, speed);

	}

	//-------------------------------------------------------------------------------Intake Hatch----------------------------------------------------------------------------------//

	public enum IntakeHatchState {
    	SUCK,
    	STOP;
    }

	 public IntakeHatchState mIntakeHatchState=IntakeHatchState.STOP;
	
	public void IntakeHatch(){
		mIntakeHatchState = IntakeHatchState.SUCK;
	}

	public void stopSucking(){
		mIntakeHatchState = IntakeHatchState.STOP;
	}

	public void setIntakeHatchSpeed(double speed) {
		mMrHuck.set(ControlMode.PercentOutput, speed);
		mMrHuckJr.set(ControlMode.PercentOutput, speed);
	}

	public IntakeHatchState getIntakeHatchState() {
		return mIntakeHatchState;
	}

//-------------------------------------------------------------------------------Rotary Intake Control ----------------------------------------------------------------------------------//

public enum IntakeRotaryState {
	CARGOStart,
	CARGOStop,
	HATCHStart,
	HATCHStop;
}

 private IntakeRotaryState mIntakeRotaryState=IntakeRotaryState.HATCHStart;

 public IntakeRotaryState getIntakeRotaryState() {
	return mIntakeRotaryState;
}

 public void SetIntakeRotaryCargoState(){
	 if (!(mIntakeRotaryState==IntakeRotaryState.CARGOStart || mIntakeRotaryState==IntakeRotaryState.CARGOStop)){
		mIntakeRotaryState = IntakeRotaryState.CARGOStart;
	 }
}

public void SetIntakeRotaryHatchState(){
	if (!(mIntakeRotaryState==IntakeRotaryState.HATCHStart || mIntakeRotaryState==IntakeRotaryState.HATCHStop)){
		mIntakeRotaryState = IntakeRotaryState.HATCHStart;
		System.out.println("Go To Hatch");
	}
}

public void SetIntakeRotaryCargo(){
	
		//Move To Cargo Limit
		if (mIntakeCargoLimit.get() == true)
		{
			mIntakeRotary.set(ControlMode.PercentOutput, .25);
		}
		else
		{
		//Brake
		mIntakeRotaryState=IntakeRotaryState.CARGOStop;
		mIntakeRotary.set(ControlMode.PercentOutput, 0);
		}
			
}

public void SetIntakeRotaryHatch(){

	//Checks if it is right state
	if (mIntakeHatchLimit.get() == true)
	{	
		mIntakeRotary.set(ControlMode.PercentOutput, -.25);
		System.out.println("Going to Hatch");
	}
	else
	{
		//Brake
		mIntakeRotaryState=IntakeRotaryState.HATCHStop;
		System.out.println("At Hatch");
		mIntakeRotary.set(ControlMode.PercentOutput, 0);
	}

}

//--------------------------------------------------------------------------------Important Robot Stuff ----------------------------------------------------------------------------------//
		
	@Override
	public void updateSubsystem()
	{

		switch(mIntakeCargoState) {

		case INTAKE:
			setCargoIntakeSpeed(1);
			break;
		case OUTTAKE:
			setCargoIntakeSpeed(-1);
			break;
		case STOP:
			setCargoIntakeSpeed(0);
			break;
		default:
			mIntakeCargoState = IntakeCargoState.STOP;
			break;
		}

		switch(mIntakeHatchState) {

			case SUCK:
				setIntakeHatchSpeed(1.0);
				break;
			case STOP:
				setIntakeHatchSpeed(0);
				break;
			default:
				mIntakeHatchState = IntakeHatchState.STOP;
				break;
			}

			switch(mIntakeRotaryState) {

			case CARGOStart:
				SetIntakeRotaryCargo();
				break;
			case HATCHStart:
				SetIntakeRotaryHatch();
				break;
			default:
			mIntakeRotary.set(ControlMode.PercentOutput, 0);
				break;
			}

		outputToSmartDashboard();
	}


	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putString("Intake_Hatch_State", mIntakeHatchState.toString());
		SmartDashboard.putString("Intake_Cargo_State", mIntakeCargoState.toString());
	}

	@Override
	public void stop(){
		mIntakeRotaryState = IntakeRotaryState.HATCHStop;
		//System.out.println("Stopping Intake");
	}

}