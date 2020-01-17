package nl.hsleiden.app.modules.product.resources;

import nl.hsleiden.app.modules.product.models.Product;
import nl.hsleiden.app.modules.product.resources.params.GameCreateParam;
import nl.hsleiden.app.modules.product.services.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/game")
@Produces({MediaType.APPLICATION_JSON})
public class ProductResource {

    @GET
    @Path("/all") // get all games
    public List<Product> getAllGames() {
        return ProductService.getAllGames();
    }

    @GET
    @Path("/new") // get four new games
    public List<Product> getFourNewGames() {
        return ProductService.getFourNewGames();
    }

    @GET
    @Path("/popular") // get four popular games
    public List<Product> getFourPopularGames() {
        return ProductService.getFourPopularGames();
    }

    @GET
    @Path("/free") // get four free games
    public List<Product> getFourFreeGames() {
        return ProductService.getFourFreeGames();
    }

    @GET
    @Path("/find/{id}") // find game by id
    public Product findGameById(
            @PathParam("id") long id
    ) {
        return ProductService.findGameById(id);
    }

    @GET
    @Path("/find") // find game with search result
    public List<Product> findGameByTitle(
            @QueryParam("searchString") String result
    ) {
        return ProductService.findGameByTitle(result);
    }

    @POST
    @Path("/add") // add game
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Product addNewGame(
            @BeanParam GameCreateParam gameCreateParam
    ) {
        return ProductService.addNewGame(
                new Product(
                    gameCreateParam.getName(),
                    gameCreateParam.getDescription(),
                    gameCreateParam.getPrice(),
                    gameCreateParam.getImagePath()
                )
        );
    }

    @DELETE
    @Path("/{id}/delete") // delete game with ID
    public void deleteGameById(
            @PathParam("id") long id
    ) {
        ProductService.deleteGameById(id);
    }

    @PUT
    @Path("/{id}/edit") // edit game with ID
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void editGameById(
            @PathParam("id") long id,
            @BeanParam GameCreateParam gameCreateParam

    ) {
        ProductService.editGameById(
                id,
                new Product(
                        gameCreateParam.getName(),
                        gameCreateParam.getDescription(),
                        gameCreateParam.getPrice(),
                        gameCreateParam.getImagePath()
                )
        );
    }

    @PUT
    @Path("/{id}/seen/add") // increase seen counter from game
    public void addGameCounterById(
            @PathParam("id") long id
    ) {
        ProductService.addGameCounterById(id);
    }
}
