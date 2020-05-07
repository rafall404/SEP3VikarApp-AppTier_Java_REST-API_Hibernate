package org.dbAccess.dbHandlers;

import org.rest.model.Job;
import org.rest.model.Job_;
import org.rest.model.User;

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
        Job j = job;
        j.setLocation(j.getLocation().toLowerCase());
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("username"), username));
        User res = manager.createQuery(cq).getSingleResult();
        res.addJob(j);
        manager.getTransaction().begin();
        manager.persist(res);
        manager.persist(j);
        manager.getTransaction().commit();
        manager.close();
    }

    public List<Job> findJobs(String location, Long id){
        List<Job> jobs = new ArrayList<>();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Job> cq = cb.createQuery(Job.class);
        Root<Job> root = cq.from(Job.class);

        String queryString = "Select j from Job j where j.location = :location and j.id > :id ORDER BY j.id asc";
        Query query = manager.createQuery(queryString);
        query.setParameter("location", location.toLowerCase());
        query.setParameter("id", id);



        query.setMaxResults(8);



        jobs = query.getResultList();
        return jobs;
    }

}
