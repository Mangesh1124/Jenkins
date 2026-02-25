import java.util.Scanner;

class Account {
    int bal;

    Account(int b) {
        bal = b;
    }

    boolean isSufficient(int wb) {
        if (bal >= wb) return true;
        else return false;
    }

    void withdraw(int wb) {
        bal = bal - wb;
        System.out.println("WITHDRAW SUCCESFULL");
        System.out.println("CURRENT BALANCE IS :-" + bal);
    }

}

class Customer implements Runnable {
    Account g1;
    String name;

    Customer(Account x1, String s) {
        g1 = x1;
        name = s;
    }

    public void run() {
        synchronized (g1) {
            Scanner s1 = new Scanner(System.in);
            System.out.println(name + " :-ENTER AMOUNT");
            int amt = s1.nextInt();
            if (g1.isSufficient(amt)) {
                g1.withdraw(amt);
            } else {
                System.out.println("INSUFFICIENT BALANCE");
            }
        }
    }

}

public class SyncT {
    public static void main(String[] args) {
        Account a1 = new Account(1000);
        Customer c1 = new Customer(a1, "Mangesh");
        Customer c2 = new Customer(a1, "Yogesh");

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        t1.start();
        t2.start();
    }
}
