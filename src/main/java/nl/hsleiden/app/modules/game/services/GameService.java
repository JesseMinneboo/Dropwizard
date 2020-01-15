package nl.hsleiden.app.modules.game.services;


import nl.hsleiden.app.modules.game.GameModule;
import nl.hsleiden.app.modules.game.dao.GameDao;
import nl.hsleiden.app.modules.game.models.Game;
import nl.hsleiden.app.services.CoreService;

import java.util.List;


public class GameService extends CoreService {

    public static List<Game> getFourNewGames() {
        return getDao().getLatestGamesFromDatabase();
    }

    public static List<Game> getFourPopularGames() {
        return getDao().getPopularGamesFromDatabase();
    }

    public static List<Game> getFourFreeGames() {
        return getDao().getFreeGamesFromDatabase();
    }

    public static List<Game> getAllGames() {
        return getDao().getAllGamesFromDatabase();
    }

    private static Game getGame(long gameId) {
        return getDao().findGameByIdInDatabase(gameId);
    }

    public static Game addNewGame(
            String gameName,
            String gameDescription,
            double gamePrice,
            String gameImagePath
    ) {
        long gameId = getDao().insertGameIntoDatabase(gameName, gameDescription, gamePrice, gameImagePath);
        Game newGame = getDao().findGameByIdInDatabase(gameId);
        return newGame;
    }

    public static boolean deleteGameById(long gameId) {
        Game game = GameService.getGame(gameId);
        getDao().deleteGameByIdFromDatabase(game.getId());
        return true;
    }

    public static boolean editGameById(
            long gameId,
            String gameName,
            String gameDescription,
            double gamePrice,
            String gameImagePath
    ) {
        Game game = GameService.getGame(gameId);
        getDao().editGameByIdFromDatabase(
                game.getId(),
                gameName,
                gameDescription,
                gamePrice,
                gameImagePath);

        return true;
    }

    public static void addGameCounterById(Long gameId) {
        long counterOld = getDao().getGameCounterFromGameId(gameId);
        long counterNew = counterOld + 1;
        getDao().AddGameCounter(counterNew, gameId);
    }

    public static List<Game> findGameByTitle(String result) {
        return getDao().findGameByTitleInDatabase("%" + result + "%");
    }

    public static Game findGameById(long gameId) {
        return getDao().findGameByIdInDatabase(gameId);
    }


    private static GameDao getDao() {
        return getDao(GameModule.MODULE_TYPE, GameDao.class);
    }
}
