package nl.hsleiden.app.daos;

import nl.hsleiden.app.daos.product.ProductMapper;
import nl.hsleiden.app.daos.user.UserMapper;
import nl.hsleiden.app.models.Product;
import nl.hsleiden.app.models.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;

public interface DAO {
    // ========================================= USERS =========================================

    /**
     * @author Jesse Minneboo
     * @return user object
     */
    @SqlQuery("SELECT * FROM user")
    @Mapper(UserMapper.class)
    List<User> getAllUsersFromDatabase();


    /**
     * @author Jesse Minneboo
     * @param limit query limit
     * @return user object
     */
    @SqlQuery("SELECT * FROM user LIMIT :limit")
    @Mapper(UserMapper.class)
    List<User> getAllUsersFromDatabase(@Bind("limit") int limit);


    /**
     * @author Jesse Minneboo
     * @param firstname firstname
     * @param surname surname
     * @param username username
     * @param password password
     * @return user object
     */
    @SqlUpdate("INSERT INTO user (user_name, user_surname, user_username, user_password) VALUES (:firstname, :surname, :username, MD5(:password))")
    @GetGeneratedKeys
    long insertUserIntoDatabase(
            @Bind("firstname") String firstname,
            @Bind("surname") String surname,
            @Bind("username") String username,
            @Bind("password") String password
    );


    /**
     * @author Jesse Minneboo
     * @param userId primary key
     * @return user object
     */
    @SqlQuery("SELECT * FROM user WHERE user_id = :userId")
    @Mapper(UserMapper.class)
    User findUserById(@Bind("userId") long userId);


    /**
     * @author Jesse Minneboo
     * @param username username
     * @param password password
     * @return user object
     */
    @SqlQuery("SELECT * FROM user WHERE user_username = :username AND user_password = MD5(:password)")
    @Mapper(UserMapper.class)
    User loginUser(@Bind("username") String username, @Bind("password") String password);


    // ========================================= PRODUCTS =========================================

    /**
     * @author Jesse Minneboo
     * @return product array containing six products
     */
    @SqlQuery("SELECT * FROM product ORDER BY product_id DESC LIMIT 6")
    @Mapper(ProductMapper.class)
    List<Product> getLatersProductsFromDatabase();


    /**
     * @author Jesse Minneboo
     * @param productName product name
     * @param productDescription product description
     * @param productPrice product price
     * @param productImagePath product image path to image
     * @return product object
     */
    @SqlUpdate("INSERT INTO product (product_name, product_description, product_price, product_image_path) VALUES (:name, :description, :price, :imagePath)")
    @GetGeneratedKeys
    long insertProductIntoDatabase(
            @Bind("name") String productName,
            @Bind("description") String productDescription,
            @Bind("price") double productPrice,
            @Bind("imagePath") String productImagePath
    );


    /**
     * @author Jesse Minneboo
     * @param productId product id
     * @return product object
     */
    @SqlQuery("SELECT * FROM product WHERE product_id = :productId")
    @Mapper(ProductMapper.class)
    Product findProductByIdInDatabase(@Bind("productId") long productId);


    /**
     * @author Jesse Minneboo
     * @param limit limit
     * @return product array
     */
    @SqlQuery("SELECT * FROM product LIMIT :limit")
    @Mapper(ProductMapper.class)
    List<Product> getAllProductsFromDatabase(@Bind("limit") int limit);


    /**
     * @author Jesse Minneboo
     * @return product array
     */
    @SqlQuery("SELECT * FROM product")
    @Mapper(ProductMapper.class)
    List<Product> getAllProductsFromDatabase();
}
