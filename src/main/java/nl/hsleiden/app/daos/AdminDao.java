package nl.hsleiden.app.daos;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;


/**
 * @auhtor Jesse Minneboo
 */
public interface AdminDao {
    @SqlQuery("SELECT counter FROM admin_website_searches")
    long getWebsiteSearchesCounter();


    @SqlUpdate("UPDATE admin_website_searches SET counter = :counter_now + 1")
    void addToSearchesCounter(@Bind("counter_now") long counterNow);


    @SqlUpdate("UPDATE admin_money_earned SET counter = :counter_now + :money")
    void addMoneyToMoneyEarned(
            @Bind("money") float money,
            @Bind("counter_now") float counterNow
        );


    @SqlQuery("SELECT counter FROM admin_money_earned")
    float getMoneyFromEarned();


    @SqlQuery("SELECT counter FROM admin_games_bought")
    long getSoldGames();


    @SqlUpdate("UPDATE admin_games_bought SET counter = :counter_now + 1")
    void addGameToSold(@Bind("counter_now") long counterNow);


    @SqlQuery("SELECT COUNT(game_id) FROM game")
    long getStock();
}
