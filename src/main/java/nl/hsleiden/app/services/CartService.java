package nl.hsleiden.app.services;

import nl.hsleiden.app.daos.CartDao;
import nl.hsleiden.app.daos.models.Game;
import nl.hsleiden.app.daos.models.Item;
import java.util.List;


/**
 * @author Jesse Minneboo
 */
public class CartService {
    private static CartDao cartDao;

    public CartService(CartDao cartDao) {
        CartService.cartDao = cartDao;
    }


    public List<Game> getAllGamesFromCart(long userId) {
        return cartDao.getAllShoppingCartItemsFromDatabase(userId);
    }


    public List<Game> getALlGamesFromCart(int limit, long userId) {
        return cartDao.getAllShoppingCartItemsFromDatabase(limit, userId);
    }


    public Item addItemToCart(long userId, long gameId) {
        long cartId = cartDao.insertItemToCartInDatabase(userId, gameId);
        Item newItem = cartDao.findCartItemById(cartId);
        return newItem;
    }


    public boolean deleteItemFromCart(long userId, long gameId) {
        long cartId = cartDao.findCartIdWithGameIdAndUserId(gameId, userId);
        cartDao.deleteItemFromCart(cartId);
        return true;
    }
}
