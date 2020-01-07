package nl.hsleiden.app.services;

import nl.hsleiden.app.daos.AdminDao;


/**
 * @author Jesse Minneboo
 */
public class AdminService {
    private static AdminDao adminDao;

    public AdminService(AdminDao adminDao) {
        AdminService.adminDao = adminDao;
    }


    public void addSearchesToCounter() {
        long counterNow = adminDao.getWebsiteSearchesCounter();
        System.out.println(counterNow);
        adminDao.addToSearchesCounter(counterNow);
    }


    public long getWebsiteSearches() {
        return adminDao.getWebsiteSearchesCounter();
    }


    public void addMoneyToMoneyEarned(float money) {
        float moneyNow = adminDao.getMoneyFromEarned();
        adminDao.addMoneyToMoneyEarned(money, moneyNow);
    }


    public float getMoneyFromEarned() {
        return adminDao.getMoneyFromEarned();
    }


    public void addGameToSold() {
        long counterNow = adminDao.getSoldGames();
        adminDao.addGameToSold(counterNow);
    }


    public long getGamesSold() {
        return adminDao.getSoldGames();
    }


    public long getStock() {
        return adminDao.getStock();
    }
}
