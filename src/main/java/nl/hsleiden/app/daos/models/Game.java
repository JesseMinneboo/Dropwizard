package nl.hsleiden.app.daos.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {
    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private double price;

    @JsonProperty
    private String imagePath;

    @JsonProperty
    private long gameCounter;

    public Game(long id, String name, String description, double price, String imagePath, long counter) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setImagePath(imagePath);
        this.setGameCounter(counter);
    }

    public long getGameCounter() {
        return gameCounter;
    }

    public void setGameCounter(long gameCounter) {
        this.gameCounter = gameCounter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
