package jdbc;

import code.accountsManager.AccountManager;
import jdbc.accountsjdbc.AccountsJDBC;

import java.sql.SQLException;

public class JDBCMain {
    public static void main(String[] args) {
        AccountsJDBC jdbc = AccountsJDBC.getInstance();
        System.out.println(jdbc + "Place 1");
        Thread thread = new Thread(jdbc);
        thread.start();
    }
}
