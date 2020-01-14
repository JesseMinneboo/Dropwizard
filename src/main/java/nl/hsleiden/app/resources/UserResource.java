package nl.hsleiden.app.resources;

import nl.hsleiden.app.DropwizardApplication;
import nl.hsleiden.app.daos.models.User;
import nl.hsleiden.app.services.UserService;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * @author Jesse Minneboo
 */
@Path("/user")
public class UserResource {
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User postLoginUser(
            @FormParam("email") String email,
            @FormParam("password") String password
    ) {
        User authenticatedUser = userService.getAuthenticatedUser(email, password);

        if(authenticatedUser != null)
            authenticatedUser.setJwt(
                    DropwizardApplication.tokenProvider
                    .generateToken(authenticatedUser.getId())
            );

        return authenticatedUser;
    }

    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User postRegisterUser(
            @NotNull @FormParam("email") String email,
            @NotNull @FormParam("name") String name,
            @NotNull @FormParam("surname") String surname,
            @NotNull @FormParam("password") String password
    ) {
        return userService.registerUser(
                email,
                name,
                surname,
                password
        );
    }
}
