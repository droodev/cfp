package example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

/**
 * Created by drew on 2015-05-12.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Whats upS";
    }

    public static void main(String[] args) throws IOException {
        /*URI baseUri = UriBuilder.fromUri("http://localhost/").port(8088).build();
        ResourceConfig config = new ResourceConfig(HelloWorld.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/helloworld");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");*/
    }
}
