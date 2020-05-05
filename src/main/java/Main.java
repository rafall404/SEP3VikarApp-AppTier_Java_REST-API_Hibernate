import org.rest.model.Apply;
import org.rest.model.Job;
import org.rest.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
//    private static final SessionFactory ourSessionFactory;
//
//    static {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//
//            ourSessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }

//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }

    public static void main(final String[] args) throws Exception {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("vikarDefault");

//        User u = new User("java",
//                "123",
//                UUID.randomUUID().toString(),
//                UUID.randomUUID().toString(),
//                UUID.randomUUID().toString(),
//                UUID.randomUUID().toString());
        EntityManager em = factory.createEntityManager();
//
//        for (int i =0; i<10; i++) {
//            Job j = new Job(UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(),
//                    "Horsens",
//                    LocalDate.now());
//            j.setPrice(42);
//            em.getTransaction().begin();
//
//            em.persist(j);
//            em.getTransaction().commit();
//
//        }

//        em.persist(u);


//        Apply apply = new Apply(u.getId(), j.getId(), false);
//
//        em.getTransaction().begin();
//        em.persist(apply);
//        em.getTransaction().commit();
//
//        User u2 = em.find(User.class, u.getId());
//        System.out.println(u);
//        System.out.println(u2);
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> cq = cb.createQuery(User.class);
//        Root<User> root = cq.from(User.class);
//        cq.select(root).where(cb.equal(root.get("fname"), u.getFname()));
//        User res = em.createQuery(cq).getSingleResult();
//        System.out.println("####################################################################");
//        System.out.println(res);
//
//        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.fname = :fname", User.class);
//        User u3 = q.setParameter("fname", u.getFname()).getSingleResult();
//        System.out.println(u3);

//        TypedQuery<User> q = em.createQuery("SELECT u from User u where username = 'java'", User.class);
//        List<User> result = q.getResultList();
//        for(int i=0;i<result.size();i++)
//        {
//            System.out.println(result.get(i).getPostedJobs().get(1) + "###################");
//        }
        em.close();

        factory.close();

//        // in data access layer
//        EntityManager em = factory.createEntityManager();
//        em.getTransaction().begin();
//
//        Job j = em.find(Job.class, 1L);
//        j.setLocation("new location");
//
//        em.getTransaction().commit();

//        final Session session = getSession();
//        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//        }

    }
}