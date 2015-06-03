package example;

import pl.edu.agh.model.Journal;
import pl.edu.agh.services.JournalService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/helloworld")
@Stateless
public class HelloWorld {

    @EJB
    private JournalService journalService;

    @Path("/new")
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        return Long.toString(journalService.addJournal(new Journal("irl", "name", "consent")));
    }

    @Path("/get/{id}")
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage(@PathParam("id") String stringId) {
        long id = Long.parseLong(stringId);
        return journalService.getJournal(id).toString();
    }

}