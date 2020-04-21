package jdbc.accountsjdbc;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountsJDBC implements Runnable{
    private Statement st = null;
    private Connection con = null;
    private static AccountsJDBC databaseConnector;
    private static Lock lock = new ReentrantLock();


    private AccountsJDBC() {
    }

    public static AccountsJDBC getInstance() {
        if (databaseConnector == null) {
            synchronized (lock) {
                if (databaseConnector == null) {
                    databaseConnector = new AccountsJDBC();
                }
            }
        }
        return databaseConnector;
    }

    public boolean checkIfExists(String username, String password) throws SQLException {
        System.out.println("User tried to check for " +  "username: " +username + " and " + "password: " + password + " in database ");
        String statement = "SELECT * FROM " + "\"sep3\"" + ".account WHERE username = " + "'" + username + "' and  password =" + "'" + password + "'";
      if(st==null)
      {
          run();
      }
        ResultSet  set = st.executeQuery(statement);
            int count= 0;
        while(set.next())
        {
            System.out.println(count);
            count++;
        }
        if(count==0)
        {
            return false;
        }
        else
            return true;
    }

    @Override
    public void run() {

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "qwerty");
            System.out.println(st);
            st = con.createStatement();
            System.out.println(st);

            } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
}
