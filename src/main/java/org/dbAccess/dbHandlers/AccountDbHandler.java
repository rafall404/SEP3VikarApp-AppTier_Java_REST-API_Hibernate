package org.dbAccess.dbHandlers;

import org.rest.model.*;
import org.rest.service.accountServer.notify.GetNotificationsDTO;
import org.rest.service.accountServer.userjobs.AccountDTO;

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

    public AccountDTO getUser(User user){

        User res;
        AccountDTO acc = new AccountDTO();
        try {

            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.and(cb.equal(root.get("username"), user.getUsername())),cb.equal(root.get("password"), user.getPassword()));

             res = manager.createQuery(cq).getSingleResult();
             acc.setEmail(res.getEmailAddress());
             acc.setFirstName(res.getFname());
             acc.setUserId(res.getId());
             acc.setLastName(res.getLname());
             acc.setPassword(res.getPassword());
             acc.setPhone(res.getTelephoneNumber());
             acc.setUsername(res.getUsername());
             acc.setPostedJobsNo(res.getPostedJobs().size());
            System.out.println(res);
            manager.close();

        }  catch(NoResultException e){
            return null;
        }
        return acc;
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
    }

    public List<GetNotificationsDTO> getNotifications(Long userId){
        List<GetNotificationsDTO> list;

        String queryString = "Select j.id, j.title, n.status from Job j join Notify n join User u where u.id = :userId";
        Query query = manager.createQuery(queryString);
        query.setParameter("userId", userId);

        list = (List<GetNotificationsDTO>) query.getResultList();
        return  list;
    }
}
