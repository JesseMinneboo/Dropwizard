package nl.hsleiden.app.modules.product.dao;

import nl.hsleiden.app.MainDao;
import nl.hsleiden.app.modules.product.dao.mappers.ProductMapper;
import nl.hsleiden.app.modules.product.models.Product;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;

public interface ProductDao extends MainDao {
    @SqlQuery("SELECT * FROM product ORDER BY id DESC LIMIT 4")
    @Mapper(ProductMapper.class)
    List<Product> getLatestGamesFromDatabase();

    @SqlQuery("SELECT * FROM product ORDER BY counter DESC LIMIT 4")
    @Mapper(ProductMapper.class)
    List<Product> getPopularGamesFromDatabase();

    @SqlQuery("SELECT * FROM product WHERE price = 0.0 LIMIT 4")
    @Mapper(ProductMapper.class)
    List<Product> getFreeGamesFromDatabase();

    @SqlUpdate("INSERT INTO product (name, description, price, image_path) VALUES (:name, :description, :price, :imagePath)")
    @GetGeneratedKeys
    long insertGameIntoDatabase(@BindBean Product product);

    @SqlQuery("SELECT * FROM product WHERE id = :id")
    @Mapper(ProductMapper.class)
    Product findGameByIdInDatabase(@Bind("id") long gameId);

    @SqlQuery("SELECT * FROM product")
    @Mapper(ProductMapper.class)
    List<Product> getAllGamesFromDatabase();

    @SqlUpdate("DELETE FROM product WHERE id = :id")
    void deleteGameByIdFromDatabase(@Bind("id") long id);

    @SqlUpdate("UPDATE product SET name = :name, description = :description, price = :price, image_path = :imagePath WHERE id = :id")
    void editGameByIdFromDatabase(@Bind("id") long id, @BindBean Product product);

    @SqlQuery("SELECT counter FROM product WHERE id = :id")
    long getGameCounterFromGameId(@Bind("id") long gameId);

    @SqlUpdate("UPDATE product SET counter = :new_value WHERE id = :id")
    void AddGameCounter(
            @Bind("new_value") long counterNew,
            @Bind("id") long gameId
    );

    @SqlQuery("SELECT * FROM product WHERE name LIKE :result")
    @Mapper(ProductMapper.class)
    List<Product> findGameByTitleInDatabase(
            @Bind("result") String result
    );
}
