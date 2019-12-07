package nl.hsleiden.app.services;

import nl.hsleiden.app.daos.DAO;
import nl.hsleiden.app.models.User;

import java.util.List;

public class UserService {
    private static DAO userDao;

    public UserService(DAO userDao){
        UserService.userDao = userDao;
    }


    /**
     * @author Jesse Minneboo
     * @return user DAO
     */
    public static List<User> getAllUsers() {
        return userDao.getAllUsersFromDatabase();
    }


    /**
     * @author Jesse Minneboo
     * @param limit limit
     * @return user DAO
     */
    public static List<User> getAllUsers(int limit) {
        return userDao.getAllUsersFromDatabase(limit);
    }


    /**
     * @author Jesse Minneboo
     * @param firstname firstname
     * @param surname surname
     * @param username username
     * @param password password
     * @return user DAO
     */
    public User registerUser(String firstname, String surname, String username, String password) {
        long userId = userDao.insertUserIntoDatabase(firstname, surname, username, password);
        User newUser = userDao.findUserById(userId);
        return newUser;
    }
}
