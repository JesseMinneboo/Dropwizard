package nl.hsleiden.app.filters;

import nl.hsleiden.app.filters.bindings.AdminBinding;
import nl.hsleiden.app.filters.services.ExceptionService;
import nl.hsleiden.app.interfaces.enums.UserRoleType;
import nl.hsleiden.app.modules.user.services.UserService;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@AdminBinding
@Priority(Priorities.AUTHENTICATION + 1)
public class AdminFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext context) {
        boolean isAdmin = UserService.getAuthUser().getRoles().stream().anyMatch(
                (userRole -> UserRoleType.valueOf(userRole.getRole()) == UserRoleType.ADMIN)
        );

        if (!isAdmin) {
            ExceptionService.throwIlIllegalArgumentException(
                    this.getClass(),
                    "Authorization Failed: You are not an ADMINISTRATOR!",
                    "Authorization Failed: User is not of ADMIN type...",
                    Response.Status.FORBIDDEN
            );
        }
    }
}