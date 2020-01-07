package nl.hsleiden.app.services;

import nl.hsleiden.app.daos.UserDao;
import nl.hsleiden.app.daos.models.User;

import java.util.List;

public class UserService {
    private static UserDao userDao;

    public UserService(UserDao userDao) {
        UserService.userDao = userDao;
    }

    public static List<User> getAllUsers() {
        return userDao.getAllUsersFromDatabase();
    }

    public static List<User> getAllUsers(int limit) {
        return userDao.getAllUsersFromDatabase(limit);
    }

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

    public User loginUser(String username, String password) {
        User authenticatedUser = userDao.loginUser(username, password);
        return authenticatedUser;
    }
}
