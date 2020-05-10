package org.dbAccess.dbHandlers;

import org.rest.model.*;

import javax.persistence.*;
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
    public boolean checkIfUsernameInUse(String userName){
        String stringQuery = "Select u from User u where u.username = :username";
        Query query = manager.createQuery(stringQuery);
        query.setParameter("username", userName);
        try{
            User user = (User) query.getSingleResult();
            return true;
        }catch(NoResultException e){
            return false;
        }
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

    public List<Job> getUserJobs(Long userId,Long Jobid)
    {
       List<Job> jobs= new ArrayList<>();
       List<Job> userJobs= new ArrayList<>();
//
//        CriteriaBuilder cb = manager.getCriteriaBuilder();
//        CriteriaQuery<Job> cq = cb.createQuery(Job.class);
//        Root<User> root = cq.from(User.class);


        String queryString = "Select u.postedJobs  from User u where u.id = :userId ";


        Query query = manager.createQuery(queryString);
       query.setParameter("userId", userId);
        jobs = query.getResultList();

        for(int i =0; i<jobs.size();i++)
        {
            if(jobs.get(i).getId()>Jobid)
            {
                userJobs.add(jobs.get(i));
            }
        }
        int finish=8;
        while(true) {
            try {
                userJobs = userJobs.subList(0, finish);
            } catch (IndexOutOfBoundsException e) {
                    finish = finish -1;
            }
            break;
        }


        return userJobs;




//        Query query1 = manager.createNativeQuery("SELECT j from (SELECT u.postedJobs from user u where u.id = )");

    }


}
