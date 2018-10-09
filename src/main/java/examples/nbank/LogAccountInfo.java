package examples.nbank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * LogStatement
 * 
 * @author Gina
 */
public class LogAccountInfo {

    private final File _file;

    public LogAccountInfo() {
        _file = new File("bank-account-" + System.currentTimeMillis() + ".log");
    }

    public boolean log(Account account) {
        new LoggingThread(account).start();
        return true;
    }

    private class LoggingThread extends Thread {

        private Account _account;

        public LoggingThread(Account account) {
            _account = account;
            setName("logging-thread-" + account.getID());
        }

        public void run() {
            int balance = _account.getBalance();
            String id = _account.getID();
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(_file, true)));
                out.println(id + " " + balance);
                out.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
