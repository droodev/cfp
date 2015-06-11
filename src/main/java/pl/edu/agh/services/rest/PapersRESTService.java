package pl.edu.agh.services.rest;

import pl.edu.agh.managers.JournalsManager;
import pl.edu.agh.managers.PapersManager;
import pl.edu.agh.model.Paper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

@Path("/papers")
@Stateless
public class PapersRESTService {

    @EJB
    private JournalsManager journalsManager;

    @EJB
    private PapersManager papersManager;

    @POST
    @Path("/")
    @Produces("application/json")
    @Consumes("application/json")
    public long createNewPaper(@QueryParam("journalID") long journalID, Paper paper) {
        return journalsManager.addPaper(journalID, paper);
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public String deletePaper(@PathParam("id") long paperID) {
        papersManager.removePaper(paperID);
        return "OK";
    }

    /*
    @GET
    @Path("/{id}/authors")
    @Produces("application/json")
    public Collection<Author> getAllAuthors(@PathParam("id") long paperID) {
        return papersManager.getAllAuthors(paperID);
    }
    */

}
