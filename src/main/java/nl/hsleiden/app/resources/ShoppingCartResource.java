package nl.hsleiden.app.resources;

import nl.hsleiden.app.daos.models.Game;
import nl.hsleiden.app.daos.models.Item;
import nl.hsleiden.app.services.ShoppingCartService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * @author Jesse Minneboo
 */
@Path("/cart")
public class ShoppingCartResource {
    private ShoppingCartService shoppingCartService;

    public ShoppingCartResource(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GET
    @Path("/all") // get all items from ID
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> getAllGamesFromShoppingCart(
            @FormParam("user_id") long userId,
            @QueryParam("limit") int limit
    ) {
        if(limit < 1)
            return shoppingCartService.getAllGamesFromShoppingCart(userId);
        else
            return shoppingCartService.getALlGamesFromShoppingCart(limit, userId);
    }

    @POST
    @Path("/add") // add game to shopping cart
    @Produces({MediaType.APPLICATION_JSON})
    public Item addGameToShoppingCart(
            @FormParam("user_id") long userId,
            @FormParam("game_id") long gameId
    ) {
        return shoppingCartService.addGameToShoppingCart(userId, gameId);
    }

    @DELETE
    @Path("/delete") // delete game from shopping cart
    @Produces({MediaType.APPLICATION_JSON})
    public void deleteGameFromShoppingCart(
            @FormParam("user_id") long userId,
            @FormParam("game_id") long gameId
    ) {
        shoppingCartService.deleteGameFromShoppingCart(userId, gameId);
    }
}
