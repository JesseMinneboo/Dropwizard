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
    @Path("/latest")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> getLatestGames() {
        return gameService.getLatestGames();
    }


    @GET
    @Path("/popular")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> getPopularGames() {
        return gameService.getPopularGames();
    }


    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Game findGameById(
            @PathParam("id") long gameId
    ) {
        return gameService.findGameById(gameId);
    }


    @PUT
    @Path("/{id}/seen/add")
    @Produces({MediaType.APPLICATION_JSON})
    public void addGameCounter(
            @PathParam("id") long gameId
    ) {
        gameService.addGameCounter(gameId);
    }


    @GET
    @Path("/{id}/seen/get")
    @Produces({MediaType.APPLICATION_JSON})
    public long getGameCounter(
            @PathParam("id") long gameId
    ) {
        return gameService.getGameCounter(gameId);
    }


    @GET
    @Path("/free")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> getFreeGames() {
        return gameService.getFreeGames();
    }


    @GET
    @Path("/all")
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
    @Path("/find")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> findGameByTitle(
            @QueryParam("searchResult") String result
    ) {
        return gameService.findGameByTitle(result);
    }


    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Game addNewGame(
            @NotNull @FormParam("product_name") String productName,
            @NotNull @FormParam("product_description") String productDescription,
            @NotNull @FormParam("product_price") double productPrice,
            @NotNull @FormParam("product_image_path") String productImagePath
    ) {
        return gameService.addNewGame(
                productName,
                productDescription,
                productPrice,
                productImagePath
        );
    }


    @DELETE
    @Path("/{id}/delete")
    @Produces({MediaType.APPLICATION_JSON})
    public void deleteGame(
            @PathParam("id") long gameId
    ) {
        gameService.deleteGame(gameId);
    }


    @PUT
    @Path("/{id}/edit")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void editGame(
            @PathParam("id") long gameId,
            @FormParam("game_title") String gameTitle,
            @FormParam("game_description") String gameDescription,
            @FormParam("game_price") double gamePrice,
            @FormParam("game_image_path") String gameImagePath

    ) {
        gameService.editGame(gameId, gameTitle, gameDescription, gamePrice, gameImagePath);
    }
}
