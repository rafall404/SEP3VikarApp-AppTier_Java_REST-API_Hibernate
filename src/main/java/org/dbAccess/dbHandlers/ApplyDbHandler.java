package org.dbAccess.dbHandlers;

import org.rest.model.Apply;
import org.rest.model.Job;
import org.rest.model.User;
import org.rest.model.UserJobId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ApplyDbHandler {
    private EntityManager manager;

    public ApplyDbHandler(EntityManagerFactory factory){
        manager = factory.createEntityManager();
    }

    public Boolean applyForJob(Long userId, Long jobId){
        UserJobId userJobId = new UserJobId(userId, jobId);

        String stringQuery ="Select a from Apply a where a.id = :id";
        Query query = manager.createQuery(stringQuery);
        query.setParameter("id", userJobId);

        try {
            query.getSingleResult();
            return false; // It returns false while user already applied
        }catch(NoResultException e){
            manager.getTransaction().begin();
            Apply userIsApplying = new Apply(userJobId);

            manager.persist(userIsApplying);

            manager.getTransaction().commit();
            manager.close();
            return true; // returns true while user did't already applied and after it added to applicants list;
        }
    }


}
