import java.util.Scanner;

class BankAccount {
    private int balance = 500;
    synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited: " + amount + " | Balance: " + balance);
        notify();
    }
    synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " trying to withdraw: " + amount);
        while (balance < amount) {
            System.out.println("Insufficient balance. " + Thread.currentThread().getName() + " waiting...");
            try { wait(); } catch (Exception e) {}
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + " | Balance: " + balance);
    }
}

class DepositThread extends Thread {
    BankAccount acc;
    DepositThread(BankAccount a, String name) { super(name); acc = a; }
    public void run() {
        try { Thread.sleep(1000); } catch (Exception e) {}
        for (int i = 0; i < 4; i++) {
            acc.deposit(300);
            try { Thread.sleep(600); } catch (Exception e) {}
        }
    }
}

class WithdrawThread extends Thread {
    BankAccount acc;
    WithdrawThread(BankAccount a, String name) { super(name); acc = a; }
    public void run() {
        for (int i = 0; i < 4; i++) {
            acc.withdraw(400);
            try { Thread.sleep(700); } catch (Exception e) {}
        }
    }
}

class Stock {
    int stock = 0;
    boolean available = false;
    synchronized void addStock(int s) {
        while (available) {
            System.out.println("Producer waiting... stock full.");
            try { wait(); } catch (Exception e) {}
        }
        stock = s;
        System.out.println("Produced stock: " + stock);
        available = true;
        notify();
    }
    synchronized int getStock() {
        while (!available) {
            System.out.println("Consumer waiting... no stock yet.");
            try { wait(); } catch (Exception e) {}
        }
        System.out.println("Consumed stock: " + stock);
        available = false;
        notify();
        return stock;
    }
}

class Producer extends Thread {
    Stock s;
    Producer(Stock st) { s = st; }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            s.addStock(i);
            try { Thread.sleep(700); } catch (Exception e) {}
        }
    }
}

class Consumer extends Thread {
    Stock s;
    Consumer(Stock st) { s = st; }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            s.getStock();
            try { Thread.sleep(900); } catch (Exception e) {}
        }
    }
}

class PriorityDemo extends Thread {
    PriorityDemo(String name) { super(name); }
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + " running with priority " + getPriority());
            try { Thread.sleep(400); } catch (Exception e) {}
        }
    }
}

public class Exercise12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;

        while (true) {
            System.out.println("\n=== Lab 11 Menu ===");
            System.out.println("1. Bank Account (wait/notify)");
            System.out.println("2. Producer-Consumer");
            System.out.println("3. Thread Priority");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("\n--- Bank Account Simulation ---");
                    BankAccount acc = new BankAccount();
                    new WithdrawThread(acc, "WithdrawThread").start();
                    new DepositThread(acc, "DepositThread").start();
                    break;

                case 2:
                    System.out.println("\n--- Producer-Consumer Simulation ---");
                    Stock st = new Stock();
                    new Producer(st).start();
                    new Consumer(st).start();
                    break;

                case 3:
                    System.out.println("\n--- Thread Priority Demo ---");
                    PriorityDemo low = new PriorityDemo("Low Priority Thread");
                    PriorityDemo high = new PriorityDemo("High Priority Thread");
                    PriorityDemo norm = new PriorityDemo("Normal Priority Thread");

                    low.setPriority(Thread.MIN_PRIORITY);
                    high.setPriority(Thread.MAX_PRIORITY);
                    norm.setPriority(Thread.NORM_PRIORITY);

                    low.start();
                    norm.start();
                    high.start();
                    break;

                case 4:
                    System.out.println("\nExiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
