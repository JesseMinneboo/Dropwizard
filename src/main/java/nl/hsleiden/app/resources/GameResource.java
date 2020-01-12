package nl.hsleiden.app.resources;

import nl.hsleiden.app.daos.models.Game;
import nl.hsleiden.app.services.GameService;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * @author Jesse Minneboo
 */
@Path("/game")
public class GameResource {
    private GameService gameService;

    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    @GET
    @Path("/all") // get all games
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> getAllGames(
            @QueryParam("limit") int limit
    ) {
        if(limit < 1)
            return gameService.getAllGames();
        else
            return gameService.getAllGames(limit);
    }

    @GET
    @Path("/new") // get four new games
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> getFourNewGames() {
        return gameService.getFourNewGames();
    }

    @GET
    @Path("/popular") // get four popular games
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> getFourPopularGames() {
        return gameService.getFourPopularGames();
    }

    @GET
    @Path("/free") // get four free games
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> getFourFreeGames() {
        return gameService.getFourFreeGames();
    }

    @GET
    @Path("/find/{id}") // find game by id
    @Produces({MediaType.APPLICATION_JSON})
    public Game findGameById(
            @PathParam("id") long id
    ) {
        return gameService.findGameById(id);
    }

    @GET
    @Path("/find") // find game with search result
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> findGameByTitle(
            @QueryParam("searchString") String result
    ) {
        return gameService.findGameByTitle(result);
    }

    @POST
    @Path("/add") // add game
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Game addNewGame(
            @NotNull @FormParam("name") String productName,
            @NotNull @FormParam("description") String productDescription,
            @NotNull @FormParam("price") double productPrice,
            @NotNull @FormParam("image_path") String productImagePath
    ) {
        return gameService.addNewGame(
                productName,
                productDescription,
                productPrice,
                productImagePath
        );
    }

    @DELETE
    @Path("/{id}/delete") // delete game with ID
    @Produces({MediaType.APPLICATION_JSON})
    public void deleteGameById(
            @PathParam("id") long id
    ) {
        gameService.deleteGameById(id);
    }

    @PUT
    @Path("/{id}/edit") // edit game with ID
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void editGameById(
            @PathParam("id") long id,
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("price") double price,
            @FormParam("image_path") String imagePath

    ) {
        gameService.editGameById(id, title, description, price, imagePath);
    }

    @PUT
    @Path("/{id}/seen/add") // increase seen counter from game
    @Produces({MediaType.APPLICATION_JSON})
    public void addGameCounterById(
            @PathParam("id") long id
    ) {
        gameService.addGameCounterById(id);
    }
}
