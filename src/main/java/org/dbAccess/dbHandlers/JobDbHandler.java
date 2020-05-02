package org.dbAccess.dbHandlers;

import org.rest.model.Job;
import org.rest.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JobDbHandler {

    private EntityManager manager;

    public JobDbHandler(EntityManagerFactory factory){
        manager =  factory.createEntityManager();
    }

    public void createJob(Job job,String username)
    {
        Job j = job;
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.and(cb.equal(root.get("username"), username)));
        User res = manager.createQuery(cq).getSingleResult();
        res.addJob(j);
        manager.getTransaction().begin();
        manager.persist(res);
        manager.persist(j);
        manager.getTransaction().commit();
        manager.close();
    }

}
