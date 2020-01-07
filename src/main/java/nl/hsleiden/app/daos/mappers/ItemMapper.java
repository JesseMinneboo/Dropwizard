package nl.hsleiden.app.daos.mappers;

import nl.hsleiden.app.daos.models.Item;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Jesse Minneboo
 */
public class ItemMapper implements ResultSetMapper<Item> {

    @Override
    public Item map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Item(
                resultSet.getString("game_name"),
                resultSet.getString("game_description"),
                resultSet.getDouble("game_price")
        );
    }
}
