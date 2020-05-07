package org.dbAccess;

import org.dbAccess.dbHandlers.AccountDbHandler;
import org.dbAccess.dbHandlers.ApplyDbHandler;
import org.dbAccess.dbHandlers.JobDbHandler;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HandlersFactory {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("vikarDefault");

    private static Lock lock = new ReentrantLock();
    private static HandlersFactory handlersFactory;


    private HandlersFactory(){}

    public static HandlersFactory getInstance(){
        if (handlersFactory==null){
            synchronized (lock){
                if (handlersFactory==null){
                    handlersFactory = new HandlersFactory();
                }
            }
        }
        return handlersFactory;
    }



    public AccountDbHandler getAccountDb() {
        return new AccountDbHandler(factory);
    }

    public JobDbHandler getJobDb()
    {
        return new JobDbHandler(factory);
    }

    public ApplyDbHandler getApplyDb() {
        return new ApplyDbHandler(factory);
    }

}
