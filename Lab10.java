import java.util.Scanner;

class WeatherThread extends Thread {
    private String message;
    WeatherThread(String msg) { message = msg; }
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + ": " + message);
            try { Thread.sleep(400); } catch (Exception e) {}
        }
        System.out.println(getName() + " finished.");
    }
}

class HiRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("HI");
            try { Thread.sleep(300); } catch (Exception e) {}
        }
        System.out.println("HI thread finished.");
    }
}

class AiRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("AI");
            try { Thread.sleep(300); } catch (Exception e) {}
        }
        System.out.println("AI thread finished.");
    }
}

public class Lab10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;

        while (true) {
            System.out.println("\n=== Multithreading Program Menu ===");
            System.out.println("1. Subclass of Thread (Weather Message)");
            System.out.println("2. Runnable Interface (HI and AI)");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("\n--- Thread Subclass Demo ---");
                    WeatherThread t1 = new WeatherThread("Today is hot");
                    WeatherThread t2 = new WeatherThread("humid and sunny");
                    t1.setName("WeatherThread-1");
                    t2.setName("WeatherThread-2");
                    t1.start();
                    t2.start();
                    try {
                        t1.join();
                        t2.join();
                    } catch (Exception e) {}
                    System.out.println("Weather threads finished.\n");
                    break;

                case 2:
                    System.out.println("\n--- Runnable Interface Demo ---");
                    Thread hiThread = new Thread(new HiRunnable());
                    Thread aiThread = new Thread(new AiRunnable());
                    hiThread.start();
                    aiThread.start();
                    try {
                        hiThread.join();
                        aiThread.join();
                    } catch (Exception e) {}
                    System.out.println("HI and AI threads finished.\n");
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
 
    

