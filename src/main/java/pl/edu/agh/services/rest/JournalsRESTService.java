package pl.edu.agh.services.rest;

import pl.edu.agh.managers.JournalsManager;
import pl.edu.agh.model.Journal;
import pl.edu.agh.model.Paper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

@Path("rest/journals")
@Stateless
public class JournalsRESTService {

    @EJB
    private JournalsManager journalsManager;

    @Path("/")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public long createNewJournal(@QueryParam("name") String name,
                                 @QueryParam("consToPublish") String consentToPublish,
                                 String base64Logo) {
        return journalsManager.addJournal(new Journal(name, consentToPublish, base64Logo));
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Journal getJournal(@PathParam("id") long id) {
        Journal j = journalsManager.getJournal(id);
        if (j == null) {
            return null;
        }
        return j;
    }

    @Path("/{id}")
    @DELETE
    @Produces("application/json")
    public Response deleteJournal(@PathParam("id") long id) {
        if(journalsManager.deleteJournal(id)){
            return Response.ok("OK").build();
        }
        return Response.status(404).build();
    }

    @Path("/")
    @GET
    @Produces("application/json")
    public List<Journal> getAllJournals() {
        return journalsManager.getAll();
    }

    @GET
    @Path("/{id}/papers")
    @Produces("application/json")
    public Collection<Paper> getAllPapersOfJournal(@PathParam("id") long journalID) {
        return journalsManager.getAllPapers(journalID);
    }
}