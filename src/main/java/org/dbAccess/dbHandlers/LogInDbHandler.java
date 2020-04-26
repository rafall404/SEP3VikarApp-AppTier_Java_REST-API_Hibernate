package org.dbAccess.dbHandlers;

import org.rest.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class LogInDbHandler {

    EntityManager manager;

    public LogInDbHandler(EntityManagerFactory factory){
        manager =  factory.createEntityManager();
    }

    public Boolean checkIfExists(User user){
        try {


            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.and(cb.equal(root.get("username"), user.getUsername())),cb.equal(root.get("password"), user.getPassword()));

            User res = manager.createQuery(cq).getSingleResult();
            System.out.println(res);
        }  catch(NoResultException e){
            return false;
        }
        return true;
    }

}
