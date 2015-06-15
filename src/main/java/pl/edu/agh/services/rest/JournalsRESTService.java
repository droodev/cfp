package pl.edu.agh.services.rest;

import pl.edu.agh.managers.JournalsManager;
import pl.edu.agh.model.Journal;
import pl.edu.agh.model.Paper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.Collection;
import java.util.List;

@Path("/journals")
@Stateless
public class JournalsRESTService {

    @EJB
    private JournalsManager journalsManager;

    @Path("/")
    @POST
    @Produces("application/json")
    public long createNewJournal(@QueryParam("name") String name,
                                 @QueryParam("consToPublish") String consentToPublish,
                                 @QueryParam("logo") String base64Logo) {
        System.out.println(String.format("%s, %s, %s", name, consentToPublish, base64Logo));
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
    public String deleteJournal(@PathParam("id") long id) {
        journalsManager.deleteJournal(id);
        return "OK";
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