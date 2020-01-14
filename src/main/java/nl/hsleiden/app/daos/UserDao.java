package nl.hsleiden.app.daos;

import nl.hsleiden.app.daos.mappers.UserMapper;
import nl.hsleiden.app.daos.models.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author Jesse Minneboo
 */
public interface UserDao {
    @SqlQuery("SELECT * FROM user WHERE user_email = :email AND user_password = MD5(:password)")
    @Mapper(UserMapper.class)
    User getAuthenticatedUser(
            @Bind("email") String email,
            @Bind("password") String password
    );

    @SqlUpdate("INSERT INTO user (user_email, user_name, user_surname, user_password, user_role_id) VALUES (:email, :name, :surname, MD5(:password), 2)")
    @GetGeneratedKeys
    long insertUserIntoUsers(
            @Bind("email") String email,
            @Bind("name") String name,
            @Bind("surname") String surname,
            @Bind("password") String password
    );

    @SqlQuery("SELECT * FROM user WHERE user_id = :userId")
    @Mapper(UserMapper.class)
    User findUserById(
            @Bind("userId") long userId
    );



}
