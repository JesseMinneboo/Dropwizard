package nl.hsleiden.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty
    private long id;

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    public User(){}

    public User(long id, String username, String password){
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
    }

    // Getters and setters
    public long getId(){return id;}
    public void setId(long id){this.id = id;}
    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
}
