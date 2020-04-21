package code.accountsManager;

import java.util.ArrayList;

public class FakeJDBC {

        private ArrayList<Account> accounts = new ArrayList<Account>();

        public boolean checkIfExists(String username,String password)
        {
            System.out.println("The username is " + username + " and password is " + password);
            accounts.add(new Account("abc","123"));
            if (username.equals("abc") && password.equals("123"))
            {
                return true;
            }
            else
                return false;
        }

}
