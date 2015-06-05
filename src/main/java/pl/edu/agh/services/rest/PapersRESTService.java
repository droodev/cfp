package pl.edu.agh.services.rest;

import com.google.gson.Gson;
import pl.edu.agh.model.Paper;
import pl.edu.agh.managers.JournalsManager;
import pl.edu.agh.managers.PapersManager;


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
    @Path("/new")
    @Produces("application/json")
    public String createNewPaper(@QueryParam("journalID") long journalID,
                                 @QueryParam("name") String paperName,
                                 @QueryParam("finanacialDisc") String financialDisclosure) {
        return new Gson().toJson(journalsManager.addPaper(journalID, new Paper(paperName, financialDisclosure)));
    }

    @GET
    @Path("/delete/{id}")
    @Produces("application/json")
    public String deletePaper(@PathParam("id") long paperID) {
        papersManager.removePaper(paperID);
        return new Gson().toJson("OK");
    }

    @GET
    @Path("/{id}/getAuthors")
    @Produces("application/json")
    public String getAllAuthors(@PathParam("id") long paperID) {
        return new Gson().toJson(papersManager.getAllAuthors(paperID));
    }

}
