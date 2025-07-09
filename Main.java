public class Main {
	public static void main(String[] args) {
        Clock myClock = new Clock();

        Thread updateThread = new Thread(myClock.updateTask());
        Thread printThread = new Thread(myClock.printTask());

        updateThread.setPriority(Thread.MIN_PRIORITY); 
        printThread.setPriority(Thread.MAX_PRIORITY);   

        updateThread.start();
        printThread.start();
                
        try {
        	updateThread.join();
        	printThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread interrupted");
        }
    }
}
