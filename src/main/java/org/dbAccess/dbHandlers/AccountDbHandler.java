package org.dbAccess.dbHandlers;

import org.rest.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDbHandler {
    private EntityManager manager;

    public AccountDbHandler(EntityManagerFactory factory){
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
            manager.close();

        }  catch(NoResultException e){
            return false;
        }
        return true;
    }

    public User getUser(User user){

        User res;
        try {

            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.and(cb.equal(root.get("username"), user.getUsername())),cb.equal(root.get("password"), user.getPassword()));

             res = manager.createQuery(cq).getSingleResult();
            System.out.println(res);
            manager.close();

        }  catch(NoResultException e){
            return null;
        }
        return res;
    }

    public void addNewAccount(User user){
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        manager.close();
    }

    public List<Job> getUserJobs(Long id)
    {
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get("id"), id));

            User u = manager.createQuery(cq).getSingleResult();
            return u.getPostedJobs();
        }catch (NoResultException e)
        {
            return null;
        }


    }


}
