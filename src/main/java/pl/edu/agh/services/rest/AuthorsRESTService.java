package pl.edu.agh.services.rest;

import com.google.gson.Gson;
import pl.edu.agh.managers.AuthorsManager;
import pl.edu.agh.managers.PapersManager;
import pl.edu.agh.model.Author;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

@Path("/authors")
@Stateless
public class AuthorsRESTService {

    @EJB
    private PapersManager papersManager;

    @EJB
    private AuthorsManager authorsManager;


    @POST
    @Path("/new")
    @Produces("application/json")
    public String createNewAuthor(@QueryParam("paperID") long paperID,
                                  @QueryParam("name") String name,
                                  @QueryParam("surname") String surname,
                                  @QueryParam("affiliation") String affiliation,
                                  @QueryParam("contribution") String contribution,
                                  @QueryParam("contributionValue") int contributionValue) {
        return new Gson().toJson(papersManager.addAuthor(paperID, new Author(name, surname, affiliation, contribution, contributionValue)));
    }

    @GET
    @Path("/delete/{id}")
    @Produces("application/json")
    public String deleteAuthor(@PathParam("id") long authorID) {
        authorsManager.removeAuthor(authorID);
        return new Gson().toJson("OK");
    }
}
