package nl.hsleiden.app.modules.user.dao.mappers;

import nl.hsleiden.app.modules.user.models.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements ResultSetMapper<User> {

    @Override
    public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("password")
        );
    }
}
