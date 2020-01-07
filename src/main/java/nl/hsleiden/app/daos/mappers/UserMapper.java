package nl.hsleiden.app.daos.mappers;

import nl.hsleiden.app.daos.models.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {

    @Override
    public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString("user_name"),
                resultSet.getString("user_surname"),
                resultSet.getString("user_username"),
                resultSet.getString("user_password"),
                resultSet.getInt("user_role_id")
        );
    }
}
