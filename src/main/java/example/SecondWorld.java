package example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/sec")
public class SecondWorld {
    @GET
    @Produces("text/plain")
    public String whatNow() {
        return "Superb!";
    }
}
