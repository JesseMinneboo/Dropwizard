package nl.hsleiden.app.modules.product.dao.seeders;

import nl.hsleiden.app.interfaces.SeederMethods;
import nl.hsleiden.app.interfaces.enums.ModuleType;
import nl.hsleiden.app.interfaces.enums.UserRoleType;
import nl.hsleiden.app.modules.product.dao.ProductDao;
import nl.hsleiden.app.modules.product.models.Product;
import nl.hsleiden.app.modules.user.dao.UserDao;
import nl.hsleiden.app.modules.user.models.User;
import nl.hsleiden.app.modules.user.resources.PasswordEncryptService;
import nl.hsleiden.app.services.CoreService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ProductTableSeeder extends CoreService implements SeederMethods {

    public ProductTableSeeder(boolean needsToSeed) {
        if (needsToSeed) {
            run();
        } else {
            reset();
        }
    }

    @Override
    public boolean isAlreadySeeded() {
        try {
            return getDao(ModuleType.PRODUCT, ProductDao.class).getAllGamesFromDatabase()
                    .toArray()
                    .length > 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public void run() {
        if (!isAlreadySeeded()) {
            try {

                getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                        new Product(
                                "Stoneshard",
                                "Stoneshard is a challenging turn-based RPG set in an open world. " +
                                        "Experience the unforgiving life of a medieval mercenary: travel across " +
                                        "the war-torn kingdom, fulfill contracts, fight, mend your wounds and " +
                                        "develop your character without any restrictions. ",
                                13.49,
                                "https://steamcdn-a.akamaihd.net/steam/apps/625960/header.jpg?t=1581015960"

                        )
                );

                getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                        new Product(
                                "GreedFall",
                                "Engage in a core roleplaying experience, and forge the destiny of a " +
                                        "new world seeping with magic, and filled with riches, lost secrets, and " +
                                        "fantastic creatures. With diplomacy, deception and force, become part of " +
                                        "a living, evolving world - influence its course and shape your story. ",
                                33.49,
                                "https://steamcdn-a.akamaihd.net/steam/apps/606880/header.jpg?t=1575302145"

                        )
                );

                getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                        new Product(
                                "Black Desert Online",
                                "Black Desert Online is a sandbox, living-world MMORPG. Experience " +
                                        "fast-paced, action-packed combat, hunt monsters and huge bosses, fight with " +
                                        "friends in a guild to siege nodes and conquer castles, train your life skills " +
                                        "such as fishing, trading, crafting, cooking, and much more! ",
                                9.99,
                                "https://steamcdn-a.akamaihd.net/steam/apps/582660/header.jpg?t=1579785141"

                        )
                );


            } catch (Exception e) {
                System.err.println("Could not insert product into database...");
            }
        }
    }

    @Override
    public void reset() {

    }
}
