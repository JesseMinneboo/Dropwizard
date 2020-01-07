package nl.hsleiden.app.daos;

import nl.hsleiden.app.daos.mappers.ItemMapper;
import nl.hsleiden.app.daos.models.Game;
import nl.hsleiden.app.daos.models.Item;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface CartDao {

    @SqlQuery("SELECT g.game_name, g.game_description, g.game_price FROM game g RIGHT JOIN cart c ON g.game_id = c.cart_game_id WHERE cart_user_id = :user_id")
    @Mapper(ItemMapper.class)
    List<Game> getAllShoppingCartItemsFromDatabase(
            @Bind("user_id") long userId
    );

    @SqlQuery("SELECT g.game_name, g.game_description, g.game_price FROM game g RIGHT JOIN cart c ON g.game_id = c.cart_game_id WHERE cart_user_id = :user_id LIMIT :limit")
    @Mapper(ItemMapper.class)
    List<Game> getAllShoppingCartItemsFromDatabase(
            @Bind("limit") int limit,
            @Bind("user_id") long userId
    );

    @SqlUpdate("INSERT INTO cart (cart_user_id, cart_game_id) VALUES (:user_id, :product_id)")
    @GetGeneratedKeys
    Long insertItemToCartInDatabase(
            @Bind("user_id") long userId,
            @Bind("product_id") long productId
    );

    @SqlQuery("SELECT game.game_name, game.game_description, game.game_price FROM cart LEFT JOIN game ON game.game_id = cart.cart_game_id WHERE cart.cart_id = :cart_id")
    @Mapper(ItemMapper.class)
    Item findCartItemById(
            @Bind("cart_id") long cartId
    );

    @SqlQuery("SELECT cart_id FROM cart LEFT JOIN game ON cart.cart_game_id = game.game_id WHERE cart.cart_game_id = :game_id AND cart_user_id = :user_id ")
    long findCartIdWithGameIdAndUserId(
            @Bind("game_id") long gameId,
            @Bind("user_id") long userId
    );

    @SqlUpdate("DELETE FROM cart WHERE cart_id = :cart_id")
    void deleteItemFromCart(
            @Bind("cart_id") long cartId
    );
}