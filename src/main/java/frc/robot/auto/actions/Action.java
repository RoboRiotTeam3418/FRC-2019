package frc.robot.auto.actions;

public interface Action {
	
    abstract void start();
    
    public abstract void update();
    
    public abstract boolean isFinished();
    
    public abstract void done();
}