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

	DigitalInput mIntakeCargoLimit;
	DigitalInput mIntakeHatchLimit;
	
	boolean IntakeCargoLimit = mIntakeCargoLimit.get();
	boolean IntakeHatchLimit = mIntakeHatchLimit.get();

public Intake() {

		mIntake= new VictorSPX(Setup.kIntakeId);
		mIntake.setInverted(false);

		mMrHuck = new VictorSPX(Setup.kMrHuckId);
		mIntake.setInverted(false);

		mMrHuck = new VictorSPX(Setup.kMrHuckId);
		mIntake.setInverted(false);

		mIntake.set(ControlMode.PercentOutput, 0);
		mIntakeRotary = new TalonSRX(Setup.kIntakeRotaryId);

		mIntakeCargoLimit = new DigitalInput(Setup.kElevatorBottomProx);
		mIntakeHatchLimit = new DigitalInput(Setup.kElevatorTopProx);


		System.out.println("Intake Done Initializing.");
    }
//-------------------------------------------------------------------------------Intake Cargo----------------------------------------------------------------------------------//

    public enum IntakeCargoState {
    	INTAKE,
    	OUTTAKE,
    	STOP;
    }

	 private IntakeCargoState mIntakeCargoState;
	 
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

	 private IntakeHatchState mIntakeHatchState;
	
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

//-------------------------------------------------------------------------------Rotary Intake Control ----------------------------------------------------------------------------------//

public enum IntakeRotaryState {
	CARGO,
	HATCH;
}

 private IntakeRotaryState mIntakeRotaryState;

 public void SetIntakeRotaryCargoState(){
	mIntakeRotaryState = IntakeRotaryState.CARGO;
}

public void SetIntakeRotaryHatchState(){
	mIntakeRotaryState = IntakeRotaryState.HATCH;
}

public void SetIntakeRotaryCargo(){

	//Checks if it is right state
	if ((IntakeCargoLimit = false) && (IntakeHatchLimit = true))
	{
		//Move To Cargo Limit
		while (IntakeCargoLimit = false)
		{
			mIntakeRotary.set(ControlMode.PercentOutput, -.25);
		}

		//Brake
		mIntakeRotary.set(ControlMode.PercentOutput, 0);
	}
	else
	{
		System.out.println("ROTARY INTAKE SENSOR NOT LINED UP");
	}
	
}

public void SetIntakeRotaryHatch(){

	//Checks if it is right state
	if ((IntakeCargoLimit = true) && (IntakeHatchLimit = false))
	{
		//Move To Cargo Limit
		while (IntakeHatchLimit = false)
		{
			mIntakeRotary.set(ControlMode.PercentOutput, .25);
		}

		//Brake
		mIntakeRotary.set(ControlMode.PercentOutput, 0);
	}
	else
	{
		System.out.println("ROTARY INTAKE SENSOR NOT LINED UP");
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
				setIntakeHatchSpeed(.4);
				break;
			case STOP:
				setIntakeHatchSpeed(0);
				break;
			default:
				mIntakeHatchState = IntakeHatchState.STOP;
				break;
			}

			switch(mIntakeRotaryState) {

			case CARGO:
				SetIntakeRotaryCargoState();
				break;
			case HATCH:
				SetIntakeRotaryHatchState();
				break;
			default:
				mIntakeRotaryState = IntakeRotaryState.HATCH;
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
		mIntakeRotaryState = IntakeRotaryState.HATCH;
		System.out.println("Stopping Intake");
	}

}