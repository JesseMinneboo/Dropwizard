package nl.hsleiden.app.modules.user.resources;

import nl.hsleiden.app.filters.bindings.AdminBinding;
import nl.hsleiden.app.filters.bindings.AuthBinding;
import nl.hsleiden.app.filters.services.ExceptionService;
import nl.hsleiden.app.interfaces.enums.UserRoleType;
import nl.hsleiden.app.modules.user.models.User;
import nl.hsleiden.app.modules.user.resources.params.UserCreateParams;
import nl.hsleiden.app.modules.user.services.UserService;
import nl.hsleiden.app.utility.LookupUtil;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User postLoginUser(
            @FormParam("email") String email,
            @FormParam("password") String password
    ) {
        return UserService.getAuthUser(email, password);
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User postCreateUser(
            @Valid @BeanParam UserCreateParams userCreateParams,
            @FormParam("role") String roleType
    ) {
        if(LookupUtil.lookup(UserRoleType.class, roleType)) {
            System.out.println(userCreateParams.getEmail());
            System.out.println(userCreateParams.getName());
            System.out.println(userCreateParams.getSurname());
            System.out.println(userCreateParams.getPassword());


            return UserService.createUser(
                    new User(
                            userCreateParams.getEmail(),
                            userCreateParams.getName(),
                            userCreateParams.getSurname(),
                            userCreateParams.getPassword()
                    ),
                    UserRoleType.valueOf(roleType)
            );
        } else {
            ExceptionService.throwIlIllegalArgumentException(
                    this.getClass(),
                    "Create User Failed: Type of role was invalid! -> " + roleType,
                    "Create User Failed: Given role parameter was invalid type -> " + roleType,
                    Response.Status.BAD_REQUEST
            );
        }

        return new User();
    }

    @PUT
    @Path("/add/role")
    @AuthBinding
    @AdminBinding
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User postAddUserRole(
            @FormParam("id") long id,
            @FormParam("role") String role
    ) {
        if (LookupUtil.lookup(UserRoleType.class, role)) {
            return UserService.addRoleToUser(id, UserRoleType.valueOf(role));
        } else {
            ExceptionService.throwIlIllegalArgumentException(
                    this.getClass(),
                    "Add Role Failed: Type of role was invalid!",
                    "Add Role Failed: Given role parameter was invalid type",
                    Response.Status.BAD_REQUEST
            );

            return new User();
        }
    }

    @DELETE
    @Path("/remove/role")
    @AuthBinding
    @AdminBinding
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User postRemoveUserRole(
            @FormParam("id") long id,
            @FormParam("role") String role
    ) {
        if (LookupUtil.lookup(UserRoleType.class, role)) {
            return UserService.deleteRoleFromUser(id, UserRoleType.valueOf(role));
        } else {
            ExceptionService.throwIlIllegalArgumentException(
                    this.getClass(),
                    "Remove Role Failed: Type of role was invalid!",
                    "Remove Role Failed: Given role parameter was invalid type",
                    Response.Status.BAD_REQUEST
            );

            return new User();
        }
    }
}
