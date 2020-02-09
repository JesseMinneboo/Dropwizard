package nl.hsleiden.app.modules.user.dao.seeders;

import nl.hsleiden.app.interfaces.SeederMethods;
import nl.hsleiden.app.interfaces.enums.ModuleType;
import nl.hsleiden.app.interfaces.enums.UserRoleType;
import nl.hsleiden.app.modules.user.dao.UserDao;
import nl.hsleiden.app.modules.user.models.User;
import nl.hsleiden.app.modules.user.resources.PasswordEncryptService;
import nl.hsleiden.app.services.CoreService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserTableSeeder extends CoreService implements SeederMethods {

    public UserTableSeeder(boolean needsToSeed) {
        if (needsToSeed) {
            run();
        } else {
            reset();
        }
    }

    @Override
    public boolean isAlreadySeeded() {
        try {
            return getDao(ModuleType.USER, UserDao.class).findUserById(1)
                    .getEmail()
                    .equals("minnebroski@gmail.com");
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public void run() {
        if (!isAlreadySeeded()) {
            try {

                long userId = getDao(ModuleType.USER, UserDao.class).createUser(
                        new User(
                                "minnebroski@gmail.com",
                                "Jesse",
                                "Minneboo",
                                PasswordEncryptService.generateStrongPasswordHash("password123")
                        )
                );

                getDao(ModuleType.USER, UserDao.class).addRoleToUser(userId, UserRoleType.ADMIN);


            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                System.err.println("Could not hash password...");
            }
        }
    }

    @Override
    public void reset() {

    }
}
