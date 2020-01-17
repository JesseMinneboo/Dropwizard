package nl.hsleiden.app.modules.product.dao.seeders;

import nl.hsleiden.app.interfaces.SeederMethods;
import nl.hsleiden.app.interfaces.enums.ModuleType;
import nl.hsleiden.app.interfaces.enums.UserRoleType;
import nl.hsleiden.app.modules.product.services.ProductService;
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
        try{
            return ProductService.findGameById(1).getId() == 1;
        }catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public void run() {
//        if (!isAlreadySeeded()) {
//            try {
//
//
//
//            } catch (Exception e) {
//                System.err.println("Could not hash password...");
//            }
//        }

    }

    @Override
    public void reset() {

    }
}
