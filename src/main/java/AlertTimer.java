import java.util.Timer;
import java.util.TimerTask;

public class AlertTimer {
    Timer timer;

    public AlertTimer(){
        timer = new Timer();
        timer.schedule(new realtimeTask(),0);
    }
    class realtimeTask extends TimerTask {
        public void run(){
            System.out.println("Hello");
        }
    }

}
