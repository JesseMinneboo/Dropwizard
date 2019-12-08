package nl.hsleiden.app.resources;

import nl.hsleiden.app.models.Product;
import nl.hsleiden.app.services.ProductService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/product")
public class ProductResource {
    private ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }


    /**
     * @author Jesse Minneboo
     * @return product service
     */
    @GET
    @Path("/highlights")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Product> getHighlights() {
        return productService.getHighlights();
    }


    /**
     * @author Jesse Minneboo
     * @param productName product name
     * @param productDescription product description
     * @param productPrice product price
     * @param productImagePath product image path to image
     * @return product service
     */
    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Product addNewProduct(
            @NotNull @FormParam("product_name") String productName,
            @NotNull @FormParam("product_description") String productDescription,
            @NotNull @FormParam("product_price") double productPrice,
            @NotNull @FormParam("product_image_path") String productImagePath
    ) {
        return productService.addNewProduct(
                productName,
                productDescription,
                productPrice,
                productImagePath
        );
    }

}
