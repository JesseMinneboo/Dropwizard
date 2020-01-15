package nl.hsleiden.app.modules.game;

import io.dropwizard.setup.Environment;
import nl.hsleiden.app.interfaces.ModuleMethods;
import nl.hsleiden.app.interfaces.enums.ModuleType;
import nl.hsleiden.app.modules.game.dao.GameDao;
import nl.hsleiden.app.modules.game.resources.GameResource;
import org.skife.jdbi.v2.DBI;

public class GameModule implements ModuleMethods {
    public static final ModuleType MODULE_TYPE = ModuleType.GAME;
    public static GameDao gameDao;

    public GameModule(DBI jdbi) {
        GameModule.gameDao = jdbi.onDemand(GameDao.class);
    }

    @Override
    public void registerModuleResources(Environment environment) {
        environment.jersey().register(
                new GameResource()
        );
    }

    public static GameDao getDao() {
        return gameDao;
    }
}
