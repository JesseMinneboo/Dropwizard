package nl.hsleiden.app.resources;

import nl.hsleiden.app.services.AdminService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/admin")
public class AdminResource {
    private AdminService adminService;

    public AdminResource(AdminService adminService) {
        this.adminService = adminService;
    }

    @PUT
    @Path("/website/searches/add")
    @Produces({MediaType.APPLICATION_JSON})
    public long addWebsiteSearches() {
        return adminService.addSearchesToCounter();
    }

    @GET
    @Path("website/searches/get")
    @Produces({MediaType.APPLICATION_JSON})
    public long getWebsiteSearches() {
        return adminService.getWebsiteSearches();
    }
}
