package nl.hsleiden.app.modules.game.resources;

import nl.hsleiden.app.filters.bindings.AuthBinding;
import nl.hsleiden.app.modules.game.models.Game;
import nl.hsleiden.app.modules.game.services.GameService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/game")
@Produces({MediaType.APPLICATION_JSON})
public class GameResource {

    @GET
    @Path("/all") // get all games
    public List<Game> getAllGames() {
        return GameService.getAllGames();
    }

    @GET
    @Path("/new") // get four new games
    public List<Game> getFourNewGames() {
        return GameService.getFourNewGames();
    }

    @GET
    @Path("/popular") // get four popular games
    public List<Game> getFourPopularGames() {
        return GameService.getFourPopularGames();
    }

    @GET
    @Path("/free") // get four free games
    public List<Game> getFourFreeGames() {
        return GameService.getFourFreeGames();
    }

    @GET
    @Path("/find/{id}") // find game by id
    public Game findGameById(
            @PathParam("id") long id
    ) {
        return GameService.findGameById(id);
    }

    @GET
    @Path("/find") // find game with search result
    public List<Game> findGameByTitle(
            @QueryParam("searchString") String result
    ) {
        return GameService.findGameByTitle(result);
    }

    @POST
    @Path("/add") // add game
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Game addNewGame(
            @NotNull @FormParam("name") String productName,
            @NotNull @FormParam("description") String productDescription,
            @NotNull @FormParam("price") double productPrice,
            @NotNull @FormParam("image_path") String productImagePath
    ) {
        return GameService.addNewGame(
                productName,
                productDescription,
                productPrice,
                productImagePath
        );
    }

    @DELETE
    @Path("/{id}/delete") // delete game with ID
    public void deleteGameById(
            @PathParam("id") long id
    ) {
        GameService.deleteGameById(id);
    }

    @PUT
    @Path("/{id}/edit") // edit game with ID
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void editGameById(
            @PathParam("id") long id,
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("price") double price,
            @FormParam("image_path") String imagePath

    ) {
        GameService.editGameById(id, title, description, price, imagePath);
    }

    @PUT
    @Path("/{id}/seen/add") // increase seen counter from game
    public void addGameCounterById(
            @PathParam("id") long id
    ) {
        GameService.addGameCounterById(id);
    }
}
