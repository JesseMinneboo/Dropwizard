package nl.hsleiden.app.resources;

import nl.hsleiden.app.models.User;
import nl.hsleiden.app.services.UserService;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
public class UserResource {
    private UserService UserService;

    public UserResource(UserService userService) {
        this.UserService = userService;
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
            return UserService.getAllUsers();
        else
            return UserService.getAllUsers(limit);
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
            @NotNull @FormParam("firstname") String firstname,
            @NotNull @FormParam("surname") String surname,
            @NotNull @FormParam("username") String username,
            @NotNull @FormParam("password") String password
    ) {
        return UserService.registerUser(
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
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        return UserService.loginUser(
                username,
                password
        );
    }



}
