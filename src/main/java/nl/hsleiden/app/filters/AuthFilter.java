package nl.hsleiden.app.filters;

import nl.hsleiden.app.MainApplication;
import nl.hsleiden.app.filters.bindings.AuthBinding;
import nl.hsleiden.app.filters.services.ExceptionService;
import nl.hsleiden.app.modules.user.services.UserService;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


@Provider
@AuthBinding
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext context) {
        // Check if the user gave a Authorization key in the header
        if (!context.getHeaders().containsKey("Authorization")) {
            ExceptionService.throwIlIllegalArgumentException(
                    this.getClass(),
                    "Authorization Failed: Token not provided",
                    "Authorization key not provided",
                    Response.Status.BAD_REQUEST
            );
        }

        String token = context.getHeaders().getFirst("Authorization");

        // Check if the token is not empty
        if (token.equals("")) {
            ExceptionService.throwIlIllegalArgumentException(
                    this.getClass(),
                    "Authorization Failed: Token was empty",
                    "Token was empty in the Authorization header key",
                    Response.Status.BAD_REQUEST
            );
        }

        System.err.println(MainApplication.tokenProvider.verifyToken(token));

        // Validate the token
        if (!MainApplication.tokenProvider.verifyToken(token)) {
            ExceptionService.throwIlIllegalArgumentException(
                    this.getClass(),
                    "Invalid token!",
                    "Token verification failed!",
                    Response.Status.UNAUTHORIZED
            );
        }

        // Save user in current session
        long id = MainApplication.tokenProvider.getDecodedJWT(token).getClaim("id").asLong();
        UserService.setAuthUser(
                UserService.getUserById(id)
        );
    }
}