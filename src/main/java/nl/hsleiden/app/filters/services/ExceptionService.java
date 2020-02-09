package nl.hsleiden.app.filters.services;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ExceptionService {
    public static void throwIlIllegalArgumentException(Class source, String message, String cause, Response.Status status) {
        throw new WebApplicationException(
                message,
                new IllegalArgumentException("--- "+ source.getName() + " " + cause + " ---"),
                status);
    }
}