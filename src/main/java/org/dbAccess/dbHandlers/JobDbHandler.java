package org.dbAccess.dbHandlers;

import org.rest.model.*;
import org.rest.service.jobServer.getSpecificJobWithPoster.GetSpecificJobDTO;
import org.rest.service.jobServer.searchjobs.SearchJobsDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        manager.persist(res);
        manager.persist(j);
        manager.getTransaction().commit();
        manager.close();
    }

    public SearchJobsDTO findJobs(String location, Long id){
        List<Job> sendJobs;

        String queryString = "Select j from Job j where j.location = :location and j.id >= :id ORDER BY j.id asc";
        Query query = manager.createQuery(queryString);
        query.setParameter("location", location.toLowerCase());
        query.setParameter("id", id);

        query.setMaxResults(8);

        sendJobs = query.getResultList();

        queryString = "Select count(j) from Job j where j.location = :location";
        query = manager.createQuery(queryString);
        query.setParameter("location", location.toLowerCase());

        Long totalJobs = (Long) query.getSingleResult();

        manager.close();

        SearchJobsDTO dto = new SearchJobsDTO(sendJobs, totalJobs.intValue());
        return dto;
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

    public GetSpecificJobDTO getSpecificjobAndUserInfo(Long jobId) {

        String stringQuery= "Select a from Job a where a.id = :jobId";
        Query query = manager.createQuery(stringQuery);
        query.setParameter("jobId", jobId);
        Job job = (Job) query.getSingleResult();

        stringQuery = "select u from User u ";
        Query query1 = manager.createQuery(stringQuery);
        List<User> users = query1.getResultList();
        User returnUser = new User();

        for(int i =0;  i<users.size(); i++)
        {
            User user  =users.get(i);
            List<Job> jobs = user.getPostedJobs();
            for(int a =0; a < jobs.size();a++)
            {
                if(jobs.get(a).getId().equals(jobId))
                {
                    returnUser = user;
                }
            }
        }


        GetSpecificJobDTO getSpecificJobDTO = new GetSpecificJobDTO(job.getId(),job.getTitle(),job.getStatus(),job.getDescription(),job.getLocation(),job.getPrice(),job.getDate(),returnUser.getId(),returnUser.getUsername(),returnUser.getFname(),returnUser.getLname(),returnUser.getEmailAddress(),returnUser.getTelephoneNumber());
        System.out.println(job+ "&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(returnUser+ "@#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(getSpecificJobDTO);
        return getSpecificJobDTO;




    }
}
