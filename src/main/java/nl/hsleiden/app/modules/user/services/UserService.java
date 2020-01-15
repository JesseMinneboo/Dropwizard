package nl.hsleiden.app.modules.user.services;

import nl.hsleiden.app.MainApplication;
import nl.hsleiden.app.interfaces.enums.UserRoleType;
import nl.hsleiden.app.modules.user.UserModule;
import nl.hsleiden.app.modules.user.dao.UserDao;
import nl.hsleiden.app.modules.user.models.User;
import nl.hsleiden.app.modules.user.resources.PasswordDecryptService;
import nl.hsleiden.app.services.CoreService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class UserService extends CoreService {
    private static User AuthUser;

    public static User getAuthUser(String email, String password) {
        try{
            User authUser = getDao().findUserByEmail(email);

            if(authUser == null) {
                return null;
            }

            if(passwordIsCorrect(password, authUser.getPassword())){
                authUser.setJwt(MainApplication.tokenProvider.generateToken(authUser.getId()));
                return authUser;
            }

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Could not validate user, returning empty user...");
        }

        return new User();
    }

    public static User getAuthUser() {
        return AuthUser;
    }

    public static User createUser(User user) {
        long userId = getDao().createUser(user);
        return getUserById(userId);
    }

    public static User addRoleToUser(long userId, UserRoleType userRoleType) {
        User user = getUserById(userId);

        if (user.isValidUser()) {
            boolean isSameRole = user.getRoles().stream().anyMatch(
                    (userRole -> UserRoleType.valueOf(userRole.getRole()) == userRoleType)
            );

            if (!isSameRole) {
                getDao().addRoleToUser(userId, userRoleType);
            }
        }

        return user;
    }

    public static User deleteRoleFromUser(long userId, UserRoleType userRoleType) {
        User user = getUserById(userId);

        if (user.isValidUser()) {
            boolean isSameRole = user.getRoles().stream().anyMatch(
                    (userRole -> UserRoleType.valueOf(userRole.getRole()) == userRoleType)
            );

            if (isSameRole) {
                getDao().deleteRoleFromUser(userId, userRoleType);
            }
        }

        return user;
    }

    public static User getUserById(long id) {
        return getDao().findUserById(id);
    }

    private static boolean passwordIsCorrect(String passwordGotten, String passwordUser) throws InvalidKeySpecException, NoSuchAlgorithmException {
       return PasswordDecryptService.validatePassword(passwordGotten, passwordUser);
    }

    public static void setAuthUser(User authUser) {
        AuthUser = authUser;
    }

    private static UserDao getDao() {
        return getDao(UserModule.MODULE_TYPE, UserDao.class);
    }
}
