package nl.hsleiden.app.services;

import nl.hsleiden.app.daos.ShoppingCartDao;
import nl.hsleiden.app.daos.models.Game;
import nl.hsleiden.app.daos.models.Item;
import java.util.List;


/**
 * @author Jesse Minneboo
 */
public class ShoppingCartService {
    private static ShoppingCartDao shoppingCartDao;

    public ShoppingCartService(ShoppingCartDao shoppingCartDao) {
        ShoppingCartService.shoppingCartDao = shoppingCartDao;
    }

    public List<Game> getAllGamesFromShoppingCart(long userId) {
        return shoppingCartDao.getAllGamesFromShoppingCart(userId);
    }

    public List<Game> getALlGamesFromShoppingCart(int limit, long userId) {
        return shoppingCartDao.getAllGamesFromShoppingCart(limit, userId);
    }

    public Item addGameToShoppingCart(long userId, long gameId) {
        long cartId = shoppingCartDao.insertItemToCartInDatabase(userId, gameId);
        Item newItem = shoppingCartDao.findCartItemById(cartId);
        return newItem;
    }

    public boolean deleteGameFromShoppingCart(long userId, long gameId) {
        long cartId = shoppingCartDao.findShoppingCartId(userId, gameId);
        shoppingCartDao.deleteGameFromShoppingCart(cartId);
        return true;
    }
}
