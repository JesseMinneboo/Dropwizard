package nl.hsleiden.app.daos;

import nl.hsleiden.app.daos.user.UserMapper;
import nl.hsleiden.app.models.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;

public interface DAO {
    // USERS
    /**
     * @author Jesse Minneboo
     * @return user object
     */
    @SqlQuery("select * from user")
    @Mapper(UserMapper.class)
    List<User> getAllUsersFromDatabase();


    /**
     * @author Jesse Minneboo
     * @param limit query limit
     * @return user object
     */
    @SqlQuery("select * from user limit :limit")
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
    @SqlUpdate("insert into user (firstname, surname, username, password) values (:firstname, :surname, :username, MD5(:password))")
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
    @SqlQuery("select * from user where user_id = :userId")
    @Mapper(UserMapper.class)
    User findUserById(@Bind("userId") long userId);
}
