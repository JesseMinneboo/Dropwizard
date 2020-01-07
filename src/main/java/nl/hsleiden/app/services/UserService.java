package nl.hsleiden.app.services;

import nl.hsleiden.app.daos.UserDao;
import nl.hsleiden.app.daos.models.User;
import java.util.List;


/**
 * @author Jesse Minneboo
 */
public class UserService {
    private static UserDao userDao;

    public UserService(UserDao userDao) {
        UserService.userDao = userDao;
    }


    /**
     *
     * @return a list of all users
     */
    public static List<User> getAllUsers() {
        return userDao.getAllUsersFromDatabase();
    }


    /**
     *
     * @param limit max limit of users to fetch
     * @return a limited list of users
     */
    public static List<User> getAllUsers(int limit) {
        return userDao.getAllUsersFromDatabase(limit);
    }


    /**
     *
     * @param firstname first name of an user
     * @param surname last name of an user
     * @param username user name of an user
     * @param password password of an user
     * @return a new created user
     */
    public User registerUser(
            String firstname,
            String surname,
            String username,
            String password
    ) {
        long userId = userDao.insertUserIntoDatabase(firstname, surname, username, password);
        User newUser = userDao.findUserById(userId);
        System.out.println(newUser.getRole());
        return newUser;
    }


    /**
     *
     * @param username user name of an user
     * @param password password of an user
     * @return an authenticated user
     */
    public User loginUser(String username, String password) {
        User authenticatedUser = userDao.loginUser(username, password);
        return authenticatedUser;
    }
}
