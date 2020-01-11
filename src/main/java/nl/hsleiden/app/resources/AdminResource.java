package nl.hsleiden.app.resources;

import nl.hsleiden.app.services.AdminService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * @author Jesse Minneboo
 */
@Path("/admin")
public class AdminResource {
    private AdminService adminService;

    public AdminResource(AdminService adminService) {
        this.adminService = adminService;
    }

    @GET
    @Path("/website/games/stock")
    @Produces({MediaType.APPLICATION_JSON})
    public long getStockFromWebsite() {
        return adminService.getStock();
    }

    @PUT
    @Path("/website/searches/add")
    @Produces({MediaType.APPLICATION_JSON})
    public void addWebsiteSearches() {
         adminService.addSearchesToCounter();
    }

    @GET
    @Path("/website/searches/get")
    @Produces({MediaType.APPLICATION_JSON})
    public long getWebsiteSearches() {
        return adminService.getWebsiteSearches();
    }

    @PUT
    @Path("/website/money/add")
    @Produces({MediaType.APPLICATION_JSON})
    public void addMoneyToMoneyEarned(
            @FormParam("money") float money
    ) {
        adminService.addMoneyToMoneyEarned(money);
    }

    @GET
    @Path("/website/money/earned")
    @Produces({MediaType.APPLICATION_JSON})
    public float getMoneyFromEarned() {
        return adminService.getMoneyFromEarned();
    }

    @PUT
    @Path("/website/games/sold/add")
    @Produces({MediaType.APPLICATION_JSON})
    public void addGameToSold() {
        adminService.addGameToSold();
    }

    @GET
    @Path("/website/games/sold/all")
    @Produces({MediaType.APPLICATION_JSON})
    public long getGamesSold(){
        return adminService.getGamesSold();
    }
}
