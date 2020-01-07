package nl.hsleiden.app.daos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty
    private long id;

    @JsonProperty
    private String firstname;

    @JsonProperty
    private String surname;

    @JsonProperty
    private String username;

    @JsonIgnore
    private String password;

    @JsonProperty
    private int role;

    public User(long id, String firstname, String surname, String username, String password, int role){
        this.setId(id);
        this.setFirstname(firstname);
        this.setSurname(surname);
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
