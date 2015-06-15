/*package pl.edu.agh.utils;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSMaintenance {

    @POST
    public Response postItem() {
        System.out.println("POST");
        return Response.noContent().header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    public Response getItem() {
        System.out.println("GET");
        return Response.noContent().header("Access-Control-Allow-Origin", "*").build();
    }
}
*/