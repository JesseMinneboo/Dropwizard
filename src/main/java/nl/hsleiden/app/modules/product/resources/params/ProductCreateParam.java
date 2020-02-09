package nl.hsleiden.app.modules.product.resources.params;

import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.FormParam;

public class ProductCreateParam {
    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private double price;

    @NotEmpty
    private String imagePath;

    public String getName() {
        return name;
    }

    @FormParam("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @FormParam("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    @FormParam("price")
    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    @FormParam("image_path")
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
