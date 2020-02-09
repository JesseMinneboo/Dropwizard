package nl.hsleiden.app.services;

import nl.hsleiden.app.MainDao;
import nl.hsleiden.app.interfaces.enums.ModuleType;
import nl.hsleiden.app.modules.product.ProductModule;
import nl.hsleiden.app.modules.user.UserModule;

public class CoreService {
    protected static <T extends MainDao> T getDao(ModuleType moduleType, Class<T> tDaoClass){
        switch (moduleType) {
            case USER:
                return tDaoClass.cast(UserModule.getDao());

            case PRODUCT:
                return tDaoClass.cast(ProductModule.getDao());

            default:
                throw new IllegalStateException("ModuleType: " + moduleType);
        }
    }
}
