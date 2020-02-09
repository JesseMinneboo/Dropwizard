package nl.hsleiden.app.modules.user.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


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

    private List<UserRole> roles;

    @JsonProperty
    private String jwt;

    private String avatarUrl;

    public User(){}

    public User(long id, String email, String name, String surname, String password, String avatarUrl){
        this.setId(id);
        this.setEmail(email);
        this.setName(name);
        this.setSurname(surname);
        this.setPassword(password);
        this.setAvatarUrl(avatarUrl);
    }

    public User (String email, String name, String surname, String password) {
        this.setEmail(email);
        this.setName(name);
        this.setSurname(surname);
        this.setPassword(password);
    }

    @JsonIgnore
    public boolean isValidUser() {
        return this.getEmail() != null;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
