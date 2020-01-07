package nl.hsleiden.app.resources;

import nl.hsleiden.app.daos.models.User;
import nl.hsleiden.app.services.UserService;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * @author Jesse Minneboo
 */
@Path("/user")
public class UserResource {
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }


    /**
     *
     * @param limit the limit of users to fetch
     * @return a list of users
     */
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getAllUsers(
            @QueryParam("limit") int limit
    ) {
        if(limit < 1)
            return userService.getAllUsers();
        else
            return userService.getAllUsers(limit);
    }


    /**
     *
     * @param firstname first name of a new user
     * @param surname last name of a new user
     * @param username the username which they need to login
     * @param password a password
     * @return a newly created user
     */
    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User registerUser(
            @NotNull @FormParam("user_name") String firstname,
            @NotNull @FormParam("user_surname") String surname,
            @NotNull @FormParam("user_username") String username,
            @NotNull @FormParam("user_password") String password
    ) {
        return userService.registerUser(
                firstname,
                surname,
                username,
                password
        );
    }


    /**
     *
     * @param username the user name of a user
     * @param password the password of a user
     * @return a user if the credentials are correct
     */
    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User loginUser(
            @FormParam("user_username") String username,
            @FormParam("user_password") String password
    ) {
        return userService.loginUser(
                username,
                password
        );
    }
}
