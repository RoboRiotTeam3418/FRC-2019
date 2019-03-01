package frc.robot.auto;

public class AutoExecuter {

	private AutoModeBase mAutoMode;
    private Thread m_thread = null;


    public void setAutoRoutine(AutoModeBase newAutoMode) {

        mAutoMode = newAutoMode;

    }


    public void start() {

        if (m_thread == null) {

            m_thread = new Thread() {

                public void run() {

                    if (mAutoMode != null) {

                        mAutoMode.run();

                    }

                }

            };

            m_thread.start();

        }

    }

    

    public void stop() {

        if (mAutoMode != null) {

            mAutoMode.stop();

        }

        m_thread = null;

    }

}