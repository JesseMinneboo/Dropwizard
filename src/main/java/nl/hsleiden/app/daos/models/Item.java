package nl.hsleiden.app.daos.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private double price;

    public Item(String name, String description, double price) {
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
