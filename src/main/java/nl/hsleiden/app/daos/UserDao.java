package nl.hsleiden.app.daos;

import nl.hsleiden.app.daos.mappers.UserMapper;
import nl.hsleiden.app.daos.models.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;


/**
 * @author Jesse Minneboo
 */
public interface UserDao {


    /**
     *
     * @return a list of users
     */
    @SqlQuery("SELECT * FROM user")
    @Mapper(UserMapper.class)
    List<User> getAllUsersFromDatabase();


    /**
     *
     * @param limit limit of users to fetch
     * @return a limited list of users
     */
    @SqlQuery("SELECT * FROM user LIMIT :limit")
    @Mapper(UserMapper.class)
    List<User> getAllUsersFromDatabase(@Bind("limit") int limit);


    /**
     *
     * @param firstname first name of an user
     * @param surname last name of an user
     * @param username user name of an user
     * @param password password of an user
     * @return the user ID
     */
    @SqlUpdate("INSERT INTO user (user_name, user_surname, user_username, user_password, user_role_id) VALUES (:firstname, :surname, :username, MD5(:password), 2)")
    @GetGeneratedKeys
    long insertUserIntoDatabase(
            @Bind("firstname") String firstname,
            @Bind("surname") String surname,
            @Bind("username") String username,
            @Bind("password") String password
    );


    /**
     *
     * @param userId ID from user
     * @return a user found with their ID
     */
    @SqlQuery("SELECT * FROM user WHERE user_id = :userId")
    @Mapper(UserMapper.class)
    User findUserById(@Bind("userId") long userId);


    /**
     *
     * @param username user name of an user
     * @param password password of an user
     * @return a authenticated user
     */
    @SqlQuery("SELECT * FROM user WHERE user_username = :username AND user_password = MD5(:password)")
    @Mapper(UserMapper.class)
    User loginUser(@Bind("username") String username, @Bind("password") String password);
}
