package nl.hsleiden.app.modules.product.resources;

import nl.hsleiden.app.filters.bindings.AdminBinding;
import nl.hsleiden.app.filters.bindings.AuthBinding;
import nl.hsleiden.app.modules.product.models.Product;
import nl.hsleiden.app.modules.product.resources.params.ProductCreateParam;
import nl.hsleiden.app.modules.product.services.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/product")
@Produces({MediaType.APPLICATION_JSON})
public class ProductResource {

    @GET
    @Path("/all")
    public List<Product> getAllGames() {
        return ProductService.getAllGames();
    }

    @GET
    @Path("/new")
    public List<Product> getFourNewGames() {
        return ProductService.getFourNewGames();
    }

    @GET
    @Path("/popular")
    public List<Product> getFourPopularGames() {
        return ProductService.getFourPopularGames();
    }

    @GET
    @Path("/free")
    public List<Product> getFourFreeGames() {
        return ProductService.getFourFreeGames();
    }

    @GET
    @Path("/find/{id}")
    public Product findGameById(
            @PathParam("id") long id
    ) {
        return ProductService.findGameById(id);
    }

    @GET
    @Path("/find")
    public List<Product> findGameByTitle(
            @QueryParam("searchString") String result
    ) {
        return ProductService.findGameByTitle(result);
    }

    @POST
    @AuthBinding
    @AdminBinding
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Product addNewGame(
            @BeanParam ProductCreateParam productCreateParam
    ) {
        return ProductService.addNewGame(
                new Product(
                    productCreateParam.getName(),
                    productCreateParam.getDescription(),
                    productCreateParam.getPrice(),
                    productCreateParam.getImagePath()
                )
        );
    }

    @DELETE
    @AuthBinding
    @AdminBinding
    @Path("/{id}/delete") // delete game with ID
    public void deleteGameById(
            @PathParam("id") long id
    ) {
        ProductService.deleteGameById(id);
    }

    @PUT
    @AuthBinding
    @AdminBinding
    @Path("/{id}/edit") // edit game with ID
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void editGameById(
            @PathParam("id") long id,
            @BeanParam ProductCreateParam productCreateParam

    ) {
        ProductService.editGameById(
                id,
                new Product(
                        productCreateParam.getName(),
                        productCreateParam.getDescription(),
                        productCreateParam.getPrice(),
                        productCreateParam.getImagePath()
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
