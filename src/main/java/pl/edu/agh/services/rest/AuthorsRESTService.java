package pl.edu.agh.services.rest;

import pl.edu.agh.managers.AuthorsManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/authors")
@Stateless
public class AuthorsRESTService {

    @EJB
    private AuthorsManager authorsManager;

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public String deleteAuthor(@PathParam("id") long authorID) {
        authorsManager.removeAuthor(authorID);
        return "OK";
    }
}
