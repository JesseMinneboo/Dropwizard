package nl.hsleiden.app.daos.mappers;

import nl.hsleiden.app.daos.models.Game;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements ResultSetMapper<Game> {

    @Override
    public Game map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Game(
                resultSet.getLong("game_id"),
                resultSet.getString("game_name"),
                resultSet.getString("game_description"),
                resultSet.getDouble("game_price"),
                resultSet.getString("game_image_path")
        );
    }
}
