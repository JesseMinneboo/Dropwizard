package nl.hsleiden.app.modules.user;

import io.dropwizard.setup.Environment;
import nl.hsleiden.app.interfaces.ModuleMethods;
import nl.hsleiden.app.interfaces.enums.ModuleType;
import nl.hsleiden.app.modules.user.dao.UserDao;
import nl.hsleiden.app.modules.user.resources.UserResource;
import org.skife.jdbi.v2.DBI;

public class UserModule implements ModuleMethods {
    public static final ModuleType MODULE_TYPE = ModuleType.USER;
    private static UserDao userDao;

    public UserModule(DBI jdbi) {
        UserModule.userDao = jdbi.onDemand(UserDao.class);
    }

    @Override
    public void registerModuleResources(Environment environment) {
        environment.jersey().register(
                new UserResource()
        );
    }

    public static UserDao getDao() {
        return userDao;
    }
}
