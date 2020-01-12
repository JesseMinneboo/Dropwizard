package nl.hsleiden.app.services;

import nl.hsleiden.app.daos.GameDao;
import nl.hsleiden.app.daos.models.Game;
import java.util.List;


/**
 * @author Jesse Minneboo
 */
public class GameService {
    private static GameDao gameDao;

    public GameService(GameDao GameDao) {
        GameService.gameDao = GameDao;
    }

    public List<Game> getFourNewGames() {
        return gameDao.getLatestGamesFromDatabase();
    }

    public List<Game> getFourPopularGames() {
        return gameDao.getPopularGamesFromDatabase();
    }

    public List<Game> getFourFreeGames() {
        return gameDao.getFreeGamesFromDatabase();
    }

    public List<Game> getAllGames() {
        return gameDao.getAllGamesFromDatabase();
    }

    public List<Game> getAllGames(int limit) {
        return gameDao.getAllGamesFromDatabase(limit);
    }

    private static Game getGame(long gameId) {
        return gameDao.findGameByIdInDatabase(gameId);
    }

    public Game addNewGame(
            String gameName,
            String gameDescription,
            double gamePrice,
            String gameImagePath
    ) {
        long gameId = gameDao.insertGameIntoDatabase(gameName, gameDescription, gamePrice, gameImagePath);
        Game newGame = gameDao.findGameByIdInDatabase(gameId);
        return newGame;
    }

    public boolean deleteGameById(long gameId) {
        Game game = GameService.getGame(gameId);
        gameDao.deleteGameByIdFromDatabase(game.getId());
        return true;
    }

    public boolean editGameById(
            long gameId,
            String gameName,
            String gameDescription,
            double gamePrice,
            String gameImagePath
    ) {
        Game game = GameService.getGame(gameId);
        gameDao.editGameByIdFromDatabase(
                game.getId(),
                gameName,
                gameDescription,
                gamePrice,
                gameImagePath);

        return true;
    }

    public void addGameCounterById(Long gameId) {
        long counterOld = gameDao.getGameCounterFromGameId(gameId);
        long counterNew = counterOld + 1;
        gameDao.AddGameCounter(counterNew, gameId);
    }

    public List<Game> findGameByTitle(String result) {
        return gameDao.findGameByTitleInDatabase("%" + result + "%");
    }

    public Game findGameById(long gameId) {
        return gameDao.findGameByIdInDatabase(gameId);
    }
}
