package nl.hsleiden.app.daos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author Jesse Minneboo
 */
public class User {
    @JsonProperty
    private long id;

    @JsonProperty
    private String email;

    @JsonProperty
    private String name;

    @JsonProperty
    private String surname;

    @JsonIgnore
    private String password;

    @JsonProperty
    private int role;

    @JsonProperty
    private String jwt;

    public User(){}

    public User(long id, String email, String name, String surname, String password, int role){
        this.setId(id);
        this.setEmail(email);
        this.setName(name);
        this.setSurname(surname);
        this.setPassword(password);
        this.setRole(role);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
