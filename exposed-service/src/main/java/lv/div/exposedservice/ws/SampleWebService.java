package lv.div.exposedservice.ws;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Just a sample web service
 */
@Path("")
public class SampleWebService {

    /**
     * GET request handler.
     * <p>
     * If we add "MediaType.APPLICATION_XML" here, - we'll need some more external dependencies for generating XML output.
     * Using just "MediaType.APPLICATION_JSON" is enough in most modern cases.
     *
     * @param id ID parameter to be found in URL request
     *
     * @return GenericResponse object in JSON format
     */
    @GET
    @Path("/get")
    @Produces({MediaType.APPLICATION_JSON})
    public GenericResponse getTest(@QueryParam("id") String id) {
        final GenericResponse r = new GenericResponse();
        if ("1".equals(id)) {
            r.setResult(1);
        } else {
            r.setResult(999);
        }
        return r;
    }

    /**
     * Another GET request handler, which uses path params
     *
     * @param pathid a param from URL request path
     *
     * @return GenericResponse object in JSON format
     */
    @GET
    @Path("/get/{pathid}")
    @Produces({MediaType.APPLICATION_JSON})
    public GenericResponse getTestWithPathParams(@PathParam("pathid") String pathid) {
        final GenericResponse r = new GenericResponse();
        r.setValue("PATH param (pathid) = " + pathid);
        return r;

    }

    /**
     * POST request handler.
     *
     * @param container GenericResponse object as data container
     *
     * @return GenericResponse object in JSON format
     */
    @POST
    @Path("/post")
    @Produces({MediaType.APPLICATION_JSON})
    public GenericResponse postTest(GenericResponse container) {
        final String inputValue = container.getValue();
        final GenericResponse r = new GenericResponse();
        r.setValue("POST-ed value = " + inputValue);
        return r;
    }

    /**
     * GET request, which dumps resources to a temporary file.
     * It simply shows, that resources are packed correctly and accessible to application.
     *
     * @return GenericResponse object in JSON format
     */
    @GET
    @Path("/dump")
    @Produces({MediaType.APPLICATION_JSON})
    public GenericResponse dumpResources() {

        final String RESOURCE_FILE_NAME = "file.txt";
        InputStream is = getClass().getClassLoader().getResourceAsStream(RESOURCE_FILE_NAME);
        String tempFilePath = "";
        try {
            File tempFile = File.createTempFile(RESOURCE_FILE_NAME, ".tmp");
            tempFile.deleteOnExit();
            // This native Java method (Files.copy) available since Java 7:
            Files.copy(is, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            tempFilePath = tempFile.toPath().toString();
        } catch (IOException e) {
            e.printStackTrace(); // Hmm... something is wrong
        }

        final GenericResponse r = new GenericResponse();
        r.setValue("Resource file dumped to  " + tempFilePath);
        return r;

    }

}
