package frc.robot.subsystems;
import frc.robot.Setup;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem
{

    static Intake mInstance = new Intake();

    public static Intake getInstance() {
    	System.out.println("Before MINStance");
    	return mInstance;
    	
    }

    private VictorSPX mIntake;

public Intake() {
			
		mIntake= new VictorSPX(Setup.kIntakeId);

		mIntake.setInverted(false);
		
		System.out.println("Intake Done Initializing.");
		mIntake.set(0);
		
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






}