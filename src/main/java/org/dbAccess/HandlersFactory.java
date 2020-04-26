package org.dbAccess;

import org.dbAccess.dbHandlers.LogInDbHandler;

import javax.persistence.EntityManager;
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

    public LogInDbHandler getLogInDb(){
        return new LogInDbHandler(factory);
    }


}
