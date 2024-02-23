package ch.hftm.joshua.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/plain")
@RegisterRestClient(configKey = "purgo-malum-api")
public interface PurgoMalumClient {
    @GET
    String getValidatedText(@QueryParam("text") String text);

}
