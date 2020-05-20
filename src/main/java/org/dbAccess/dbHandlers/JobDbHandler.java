package org.dbAccess.dbHandlers;

import org.rest.model.*;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class JobDbHandler {

    private EntityManager manager;

    public JobDbHandler(EntityManagerFactory factory){
        manager =  factory.createEntityManager();
    }

    public void createJob(Job job,String username)
    {
        System.out.println(username + "$$$%%^$%^*$&^$*^$^*%$%^*$%^*$*%^$*");
        Job j = job;
        j.setLocation(j.getLocation().toLowerCase());
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("username"), username));

        User res = manager.createQuery(cq).getSingleResult();
        res.addJob(j);
        manager.getTransaction().begin();
        System.out.println(res.getUsername() + "$$$%%^$%^*$&^$*^$^*%$%^*$%^*$*%^$*");
        manager.persist(res);
        System.out.println(j.getLocation() + "%%%%%%%%%");
        manager.persist(j);
        manager.getTransaction().commit();
        manager.close();
    }

    public List<Job> findJobs(String location, Long id){
        List<Job> jobs;

        String queryString = "Select j from Job j where j.location = :location and j.id > :id ORDER BY j.id asc";
        Query query = manager.createQuery(queryString);
        query.setParameter("location", location.toLowerCase());
        query.setParameter("id", id);

        query.setMaxResults(8);

        jobs = query.getResultList();
        manager.close();
        return jobs;
    }

    public List<User> getApplicants(Long jobId){
        List<User> applicants;

        String queryString = "Select u from User u join Apply a where a.id.jobId = :jobId";
        Query query = manager.createQuery(queryString);
        query.setParameter("jobId", jobId);

        applicants = query.getResultList();
        manager.close();
        return applicants;
    }

    public void acceptApplicants(Long jobId, List<Long> usersId){
        ArrayList<User> acceptedApplicants = null;
        String queryString = "Select a from User u join Apply a where a.id.jobId = :jobId and a.id.userId = :userId";
        Query query = manager.createQuery(queryString);
        query.setParameter("jobId", jobId);
        for (int i = 0; i < usersId.size(); i++) {
            query.setParameter("userId", usersId.get(i));
            Apply application = (Apply) query.getSingleResult();
            application.setAccepted(true);

            UserJobId userJobId = new UserJobId(usersId.get(i), jobId);
            Notify notification = new Notify(userJobId, "Accepted");

            manager.getTransaction().begin();
            manager.persist(application);
            manager.persist(notification);
            manager.getTransaction().commit();
            manager.close();
        }
    }

}
