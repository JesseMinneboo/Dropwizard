package nl.hsleiden.app.services;

import nl.hsleiden.app.daos.AdminDao;

public class AdminService {
    private static AdminDao adminDao;

    public AdminService(AdminDao adminDao) {
        AdminService.adminDao = adminDao;
    }

    public int addSearchesToCounter() {
        long counterNow = adminDao.getWebsiteSearchesCounter();
        System.out.println(counterNow);
        return adminDao.addToSearchesCounter(counterNow);
    }

    public long getWebsiteSearches() {
        return adminDao.getWebsiteSearchesCounter();
    }
}
