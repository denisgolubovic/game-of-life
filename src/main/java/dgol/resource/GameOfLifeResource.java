package dgol.resource;

import dgol.model.GameBoard;
import dgol.service.GameOfLifeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;

@Path("/gameoflife")
@Consumes(MediaType.APPLICATION_JSON)
public class GameOfLifeResource {

    @Inject
    Logger logger;

    @Inject
    GameOfLifeService service;

    @POST
    @Path("/initialize")
    public Response initialize(final GameBoard request) {
        logger.info("Initializing the game");
        return Response.ok(service.initGame(request.getColumns(), request.getRows(), request.getAliveProbability()).getCells()).build();
    }

    @POST
    @Path("/next")
    public Response next() {
        logger.info("Retrieving the next generation");
        return Response.ok(service.next().getCells()).build();
    }
}
