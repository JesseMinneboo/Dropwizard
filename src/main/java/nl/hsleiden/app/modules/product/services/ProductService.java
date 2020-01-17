package nl.hsleiden.app.modules.product.services;


import nl.hsleiden.app.modules.product.ProductModule;
import nl.hsleiden.app.modules.product.dao.ProductDao;
import nl.hsleiden.app.modules.product.models.Product;
import nl.hsleiden.app.services.CoreService;

import java.util.List;


public class ProductService extends CoreService {

    public static List<Product> getFourNewGames() {
        return getDao().getLatestGamesFromDatabase();
    }

    public static List<Product> getFourPopularGames() {
        return getDao().getPopularGamesFromDatabase();
    }

    public static List<Product> getFourFreeGames() {
        return getDao().getFreeGamesFromDatabase();
    }

    public static List<Product> getAllGames() {
        return getDao().getAllGamesFromDatabase();
    }

    private static Product getGame(long gameId) {
        return getDao().findGameByIdInDatabase(gameId);
    }

    public static Product addNewGame(Product product) {
        long gameId = getDao().insertGameIntoDatabase(product);
        Product newProduct = getDao().findGameByIdInDatabase(gameId);
        return newProduct;
    }

    public static boolean deleteGameById(long gameId) {
        Product product = ProductService.getGame(gameId);
        getDao().deleteGameByIdFromDatabase(product.getId());
        return true;
    }

    public static boolean editGameById(long gameId, Product product) {
        Product newProduct = ProductService.getGame(gameId);
        getDao().editGameByIdFromDatabase(newProduct.getId(), product);

        return true;
    }

    public static void addGameCounterById(Long gameId) {
        long counterOld = getDao().getGameCounterFromGameId(gameId);
        long counterNew = counterOld + 1;
        getDao().AddGameCounter(counterNew, gameId);
    }

    public static List<Product> findGameByTitle(String result) {
        return getDao().findGameByTitleInDatabase("%" + result + "%");
    }

    public static Product findGameById(long gameId) {
        return getDao().findGameByIdInDatabase(gameId);
    }


    private static ProductDao getDao() {
        return getDao(ProductModule.MODULE_TYPE, ProductDao.class);
    }
}
