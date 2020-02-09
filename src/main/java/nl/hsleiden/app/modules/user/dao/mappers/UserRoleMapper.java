package nl.hsleiden.app.modules.user.dao.mappers;

import nl.hsleiden.app.modules.user.models.UserRole;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleMapper implements ResultSetMapper<UserRole> {
    public UserRole map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new UserRole(
                r.getLong("user_role_id") ,
                r.getLong("user_id"),
                r.getString("role")
        );
    }
}
