package example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("j");
        EntityManager em = factory.createEntityManager();
        printDB(em);

        em.getTransaction().begin();
        Ent ent = new Ent();
        ent.setName("FirstName");
        ent.setSurname("Surname");
        em.persist(ent);
        em.getTransaction().commit();

        printDB(em);

        em.close();

        return "Whats upS";
    }

    private void printDB(EntityManager em) {
        Query q = em.createQuery("select e from Ent e");
        List<Ent> res = q.getResultList();
        for (Ent e : res) {
            System.out.println(e.getSurname());
        }
        System.out.println(res.size());
    }

}
