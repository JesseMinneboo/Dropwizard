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

    public User getAuthenticatedUser(String username, String password) {
        User authenticatedUser = userDao.getAuthenticatedUser(username, password);

        if (authenticatedUser == null)
            return new User();

        return authenticatedUser;
    }

    public User registerUser(
            String username,
            String name,
            String surname,
            String password
    ) {
        long userId = userDao.insertUserIntoUsers(
                name,
                surname,
                username,
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
