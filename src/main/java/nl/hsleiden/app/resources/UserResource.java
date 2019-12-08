package nl.hsleiden.app.resources;

import nl.hsleiden.app.models.User;
import nl.hsleiden.app.services.UserService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
public class UserResource {
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }


    /**
     * @author Jesse Minneboo
     * @param limit limit
     * @return user service
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
     * @author Jesse Minneboo
     * @param firstname firstname
     * @param surname lastname
     * @param username username
     * @param password password
     * @return user service
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
     * @author Jesse Minneboo
     * @param username username
     * @param password password
     * @return user service
     */
    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User postLoginAction(
            @FormParam("user_username") String username,
            @FormParam("user_password") String password
    ) {
        return userService.loginUser(
                username,
                password
        );
    }



}
