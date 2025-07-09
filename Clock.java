import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock  {
	private volatile String currentTime;
    private volatile boolean running = true; 

    public Clock() {
        this.currentTime = getCurrentTimeString();
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void updateTime() {
        this.currentTime = getCurrentTimeString();
    }

    private String getCurrentTimeString() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        LocalDateTime date = LocalDateTime.now();
        return formatter.format(date);
    }
    
    public Runnable updateTask() {
    	return () -> {
            while (running) {
                updateTime();
                try {
                    Thread.sleep(100);//print each 100 milliseconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    running = false;
                    System.err.println("Update thread interrupted");
                }
            }
        };
    }

    public Runnable printTask() {
    	return () -> {
            while (running) {
                System.out.println(getCurrentTime());
                try {
                    Thread.sleep(1000);//print each 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    running = false;
                    System.err.println("Print thread interrupted");
                }
            }
        };
    }
    
    /*public void stopClock() {
        this.running = false;
    }*/   
}
