package nl.hsleiden.app.filters;

import nl.hsleiden.app.DropwizardApplication;
import nl.hsleiden.app.filters.bindings.AuthBinding;
import nl.hsleiden.app.services.UserService;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@AuthBinding
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        // Check if the user gave a Authorization key in the header
        if (!context.getHeaders().containsKey("Authorization")) {
            Exception cause = new IllegalArgumentException("Token not provided");
            throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
        }

        String token = context.getHeaders().getFirst("Authorization");

        // Check if the token is not empty
        if (token.equals("")) {
            Exception cause = new IllegalArgumentException("Token not provided");
            throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
        }

        System.err.println(DropwizardApplication.tokenProvider.verifyToken(token));

        // Validate the token
        if (!DropwizardApplication.tokenProvider.verifyToken(token)) {
            Exception cause = new IllegalArgumentException("Token not provided");
            throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
        }

        // Save user in current session
        long id = DropwizardApplication.tokenProvider.getDecodedJWT(token).getClaim("user_id").asLong();
        UserService.setAuthUser(
                UserService.getUserById(id)
        );
    }
}