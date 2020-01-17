package nl.hsleiden.app.modules.user.dao;

import nl.hsleiden.app.MainDao;
import nl.hsleiden.app.interfaces.enums.UserRoleType;
import nl.hsleiden.app.modules.user.dao.mappers.UserMapper;
import nl.hsleiden.app.modules.user.dao.mappers.UserRoleMapper;
import nl.hsleiden.app.modules.user.models.User;
import nl.hsleiden.app.modules.user.models.UserRole;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;


public interface UserDao extends MainDao {
    @SqlUpdate("INSERT INTO user (email, name, surname, password) VALUES (:email, :name, :surname, :password)")
    @GetGeneratedKeys
    long createUser(@BindBean User user);

    @SqlQuery("SELECT * FROM user as u LEFT JOIN user_role as ur ON ur.user_id = u.id WHERE email = :email")
    @Mapper(UserMapper.class)
    User findUserByEmail(@Bind("email") String email);

    @SqlQuery("SELECT * FROM user as u LEFT JOIN user_role as ur ON ur.user_id = u.id WHERE user_id = :id")
    @Mapper(UserMapper.class)
    User findUserById(@Bind("id") long id);

    @SqlQuery("select * from user_role where user_id = :user_id")
    @Mapper(UserRoleMapper.class)
    List<UserRole> findUserRolesByUserId(@Bind("user_id") long userId);

    @SqlUpdate("INSERT INTO user_role (user_id, role) values (:user_id, :role)")
    @GetGeneratedKeys
    long addRoleToUser(@Bind("user_id") long userId, @Bind("role") UserRoleType role);

    @SqlUpdate("DELETE FROM user_role WHERE user_id = :user_id and role = :role")
    void deleteRoleFromUser(@Bind("user_id") long userId, @Bind("role") UserRoleType role);
}
