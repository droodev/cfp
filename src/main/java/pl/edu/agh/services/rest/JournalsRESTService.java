package pl.edu.agh.services.rest;

import com.google.gson.Gson;
import pl.edu.agh.model.Journal;
import pl.edu.agh.managers.JournalsManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

@Path("/journals")
@Stateless
public class JournalsRESTService {

    @EJB
    private JournalsManager journalsManager;

    @Path("/new")
    @POST
    @Produces("application/json")
    public String createNewJournal(@QueryParam("name") String name,
                                   @QueryParam("consToPublish") String consentToPublish,
                                   @QueryParam("logo") String base64Logo) {
        return new Gson().toJson(journalsManager.addJournal(new Journal(name, consentToPublish, base64Logo)));
    }

    @Path("/get/{id}")
    @GET
    @Produces("application/json")
    public String getJournal(@PathParam("id") long id) {
        Journal j = journalsManager.getJournal(id);
        if (j == null) {
            return "No Journal";
        }
        Gson gson = new Gson();
        return gson.toJson(j);
    }

    @Path("/delete/{id}")
    @GET
    @Produces("application/json")
    public String deleteJournal(@PathParam("id") long id) {
        journalsManager.deleteJournal(id);
        return new Gson().toJson("OK");
    }

    @Path("/getAll")
    @GET
    @Produces("application/json")
    public String getAllJournals() {
        return new Gson().toJson(journalsManager.getAll());

    }

    @GET
    @Path("/{id}/getPapers")
    @Produces("application/json")
    public String getAllPapersOfJournal(@PathParam("id") long journalID) {
        Gson gson = new Gson();
        return gson.toJson(journalsManager.getAllPapers(journalID));
    }
}