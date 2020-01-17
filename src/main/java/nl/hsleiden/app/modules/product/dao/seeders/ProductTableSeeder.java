package nl.hsleiden.app.modules.product.dao.seeders;

import nl.hsleiden.app.interfaces.SeederMethods;
import nl.hsleiden.app.interfaces.enums.ModuleType;
import nl.hsleiden.app.modules.product.dao.ProductDao;
import nl.hsleiden.app.modules.product.models.Product;
import nl.hsleiden.app.modules.product.services.ProductService;
import nl.hsleiden.app.services.CoreService;


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
        try{
            return ProductService.findGameById(1).getId() == 1;
        }catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public void run() {
        try {
            getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                    new Product(
                            "Country Girl Keiko",
                            "Play as Keiko, a socially awkward woman living in a small country village. Explore the town and help her overcome her social anxiety, so she can thrive at her university. ",
                            13.29,
                            "https://steamcdn-a.akamaihd.net/steam/apps/1065940/header.jpg?t=1579231096"
                    )
            );

            getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                    new Product(
                            "Country Girl Keiko",
                            "Play as Keiko, a socially awkward woman living in a small country village. Explore the town and help her overcome her social anxiety, so she can thrive at her university. ",
                            13.29,
                            "https://steamcdn-a.akamaihd.net/steam/apps/1065940/header.jpg?t=1579231096"
                    )
            );

            getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                    new Product(
                            "Country Girl Keiko",
                            "Play as Keiko, a socially awkward woman living in a small country village. Explore the town and help her overcome her social anxiety, so she can thrive at her university. ",
                            13.29,
                            "https://steamcdn-a.akamaihd.net/steam/apps/1065940/header.jpg?t=1579231096"
                    )
            );

            getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                    new Product(
                            "Country Girl Keiko",
                            "Play as Keiko, a socially awkward woman living in a small country village. Explore the town and help her overcome her social anxiety, so she can thrive at her university. ",
                            13.29,
                            "https://steamcdn-a.akamaihd.net/steam/apps/1065940/header.jpg?t=1579231096"
                    )
            );

            getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                    new Product(
                            "Country Girl Keiko",
                            "Play as Keiko, a socially awkward woman living in a small country village. Explore the town and help her overcome her social anxiety, so she can thrive at her university. ",
                            13.29,
                            "https://steamcdn-a.akamaihd.net/steam/apps/1065940/header.jpg?t=1579231096"
                    )
            );

            getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                    new Product(
                            "Country Girl Keiko",
                            "Play as Keiko, a socially awkward woman living in a small country village. Explore the town and help her overcome her social anxiety, so she can thrive at her university. ",
                            13.29,
                            "https://steamcdn-a.akamaihd.net/steam/apps/1065940/header.jpg?t=1579231096"
                    )
            );

            getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                    new Product(
                            "Country Girl Keiko",
                            "Play as Keiko, a socially awkward woman living in a small country village. Explore the town and help her overcome her social anxiety, so she can thrive at her university. ",
                            13.29,
                            "https://steamcdn-a.akamaihd.net/steam/apps/1065940/header.jpg?t=1579231096"
                    )
            );

            getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                    new Product(
                            "Country Girl Keiko",
                            "Play as Keiko, a socially awkward woman living in a small country village. Explore the town and help her overcome her social anxiety, so she can thrive at her university. ",
                            13.29,
                            "https://steamcdn-a.akamaihd.net/steam/apps/1065940/header.jpg?t=1579231096"
                    )
            );

            getDao(ModuleType.PRODUCT, ProductDao.class).insertGameIntoDatabase(
                    new Product(
                            "Country Girl Keiko",
                            "Play as Keiko, a socially awkward woman living in a small country village. Explore the town and help her overcome her social anxiety, so she can thrive at her university. ",
                            13.29,
                            "https://steamcdn-a.akamaihd.net/steam/apps/1065940/header.jpg?t=1579231096"
                    )
            );

        }catch (Exception e) {}
    }

    @Override
    public void reset() {
    }
}
