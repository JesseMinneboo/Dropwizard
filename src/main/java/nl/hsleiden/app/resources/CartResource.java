package nl.hsleiden.app.resources;

import nl.hsleiden.app.daos.models.Game;
import nl.hsleiden.app.daos.models.Item;
import nl.hsleiden.app.services.CartService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cart")
public class CartResource {
    private CartService cartService;

    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    @GET
    @Path("/{id}/all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Game> getAllGamesFromCart (
            @PathParam("id") long userId,
            @QueryParam("limit") int limit
    ) {
        if(limit < 1)
            return cartService.getAllGamesFromCart(userId);
        else
            return cartService.getALlGamesFromCart(limit, userId);
    }

    @POST
    @Path("/{id}/add")
    @Produces({MediaType.APPLICATION_JSON})
    public Item addItemToCart (
            @PathParam("id") long userId,
            @FormParam("game_id") long gameId
    ) {
        return cartService.addItemToCart(userId, gameId);
    }

    @DELETE
    @Path("/{id}/delete")
    @Produces({MediaType.APPLICATION_JSON})
    public void deleteCartItem (
            @PathParam("id") long userId,
            @FormParam("game_id") long gameId
    ) {
        cartService.deleteItemFromCart(userId, gameId);
    }
}
