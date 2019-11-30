package nl.hsleiden.app.daos.user;

import nl.hsleiden.app.models.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {
    @Override
    public User map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
        return new User(
                r.getLong("user_id"),
                r.getString("displayname"),
                r.getString("username"),
                r.getString("password")
        );
    }
}
