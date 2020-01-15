package nl.hsleiden.app.services;

import nl.hsleiden.app.MainDao;
import nl.hsleiden.app.interfaces.enums.ModuleType;
import nl.hsleiden.app.modules.game.GameModule;
import nl.hsleiden.app.modules.user.UserModule;

public class CoreService {
    protected static <T extends MainDao> T getDao(ModuleType moduleType, Class<T> tDaoClass){
        switch (moduleType) {
            case USER:
                return tDaoClass.cast(UserModule.getDao());

            case GAME:
                return tDaoClass.cast(GameModule.getDao());

            default:
                throw new IllegalStateException("ModuleType: " + moduleType);
        }
    }
}
