package nl.hsleiden.app.daos;

import nl.hsleiden.app.daos.mappers.GameMapper;
import nl.hsleiden.app.daos.models.Game;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface GameDao {
    @SqlQuery("SELECT * FROM game ORDER BY game_id DESC LIMIT 4")
    @Mapper(GameMapper.class)
    List<Game> getLatestGamesFromDatabase();

    @SqlUpdate("INSERT INTO game (game_name, game_description, game_price, game_image_path) VALUES (:name, :description, :price, :imagePath)")
    @GetGeneratedKeys
    long insertGameIntoDatabase(
            @Bind("name") String gameName,
            @Bind("description") String gameDescription,
            @Bind("price") double gamePrice,
            @Bind("imagePath") String gameImagePath
    );

    @SqlQuery("SELECT * FROM game WHERE game_id = :game_id")
    @Mapper(GameMapper.class)
    Game findGameByIdInDatabase(@Bind("game_id") long gameId);

    @SqlQuery("SELECT * FROM game LIMIT :limit")
    @Mapper(GameMapper.class)
    List<Game> getAllGamesFromDatabase(@Bind("limit") int limit);

    @SqlQuery("SELECT * FROM game")
    @Mapper(GameMapper.class)
    List<Game> getAllGamesFromDatabase();

    @SqlUpdate("DELETE FROM game where game_id = :game_id")
    void deleteGameByIdFromDatabase(@Bind("game_id") long id);

    @SqlUpdate("UPDATE game SET game_name = :game_name, game_description = :game_description, game_price = :game_price, game_image_path = :game_image_path WHERE game_id = :game_id")
    void editGameByIdFromDatabase(
            @Bind("game_id") long id,
            @Bind("game_name") String gameName,
            @Bind("game_description") String gameDescription,
            @Bind("game_price") double gamePrice,
            @Bind("game_image_path")String gameImagePath
    );
}
