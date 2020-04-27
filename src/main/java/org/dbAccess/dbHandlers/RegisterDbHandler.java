package org.dbAccess.dbHandlers;

import org.rest.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class RegisterDbHandler {

    EntityManager manager;

    public RegisterDbHandler(EntityManagerFactory factory){
        manager =  factory.createEntityManager();
    }

    public void addNewAccount(User user){
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        manager.close();
    }

}
