package nl.hsleiden.app.modules.user.services;

import nl.hsleiden.app.MainApplication;
import nl.hsleiden.app.filters.services.ExceptionService;
import nl.hsleiden.app.interfaces.enums.UserRoleType;
import nl.hsleiden.app.modules.user.UserModule;
import nl.hsleiden.app.modules.user.dao.UserDao;
import nl.hsleiden.app.modules.user.models.User;
import nl.hsleiden.app.modules.user.models.UserRole;
import nl.hsleiden.app.modules.user.resources.PasswordDecryptService;
import nl.hsleiden.app.services.CoreService;

import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;


public class UserService extends CoreService {
    private static User AuthUser;

    public static User getAuthUser(String email, String password) {
        try{
            User authUser = getDao().findUserByEmail(email);

            if(authUser == null) {
                ExceptionService.throwIlIllegalArgumentException(
                        UserService.class,
                        "Create User Failed: Type of role was invalid! -> ",
                        "Create User Failed: Given role parameter was invalid type -> ",
                        Response.Status.UNAUTHORIZED
                );
            }

            if(passwordIsCorrect(password, authUser.getPassword())){
                List<UserRole> authUserRoles = getDao().findUserRolesByUserId(authUser.getId());
                authUser.setJwt(MainApplication.tokenProvider.generateToken(authUser.getId()));
                authUser.setRoles(authUserRoles);
                return authUser;
            } else {
                ExceptionService.throwIlIllegalArgumentException(
                        UserService.class,
                        "Create User Failed: Type of role was invalid! -> ",
                        "Create User Failed: Given role parameter was invalid type -> ",
                        Response.Status.UNAUTHORIZED
                );

            }

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Could not validate user, returning empty user...");
        }

        return new User();
    }

    public static User getAuthUser() {
        return AuthUser;
    }

    public static User createUser(User user, UserRoleType role) {
        long userId  = getDao().createUser(user);
        getDao().addRoleToUser(userId, role);

        return getUserById(userId);
    }

    public static User getUserById(long id) {
        User user = getDao().findUserById(id);
        if (user.isValidUser()) {
            List<UserRole> authUserRoles = getDao().findUserRolesByUserId(
                    user.getId()
            );

            user.setRoles(authUserRoles);
        } else {
            user = new User();
        }

        return user;
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
