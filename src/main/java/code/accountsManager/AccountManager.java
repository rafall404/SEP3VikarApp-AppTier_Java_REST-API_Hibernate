package code.accountsManager;

import jdbc.accountsjdbc.AccountsJDBC;

import java.sql.SQLException;

public class AccountManager {
    private AccountsJDBC jdbc;
    private FakeJDBC fakeJDBC;

    public AccountManager()
    {
        this.fakeJDBC= new FakeJDBC();
    }

    public boolean checkIfExists(String username, String password) {
            return   fakeJDBC.checkIfExists(username, password);
    }
}
