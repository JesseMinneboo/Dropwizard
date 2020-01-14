package nl.hsleiden.app.services;

import nl.hsleiden.app.daos.UserDao;
import nl.hsleiden.app.daos.models.User;


/**
 * @author Jesse Minneboo
 */
public class UserService {
    private static UserDao userDao;

    public UserService(UserDao userDao) {
        UserService.userDao = userDao;
    }

    public User getAuthenticatedUser(String email, String password) {
        User authenticatedUser = userDao.getAuthenticatedUser(email, password);

        if (authenticatedUser == null)
            return null;

        return authenticatedUser;
    }

    public User registerUser(
            String email,
            String name,
            String surname,
            String password
    ) {
        long userId = userDao.insertUserIntoUsers(
                email,
                surname,
                name,
                password
        );

        return userDao.findUserById(userId);
    }

    public static User getUserById(long id) {
        return userDao.findUserById(id);
    }

    public static void setAuthUser(User authUser) {
    }
}
