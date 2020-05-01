package org.dbAccess.dbHandlers;

import org.rest.model.Job;

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

    public void createJob(Job job)
    {
        Job j = job;
        manager.getTransaction().begin();
        manager.persist(j);
        manager.getTransaction().commit();
    }

}
