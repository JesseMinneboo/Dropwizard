package nl.hsleiden.app.modules.product;

import io.dropwizard.setup.Environment;
import nl.hsleiden.app.interfaces.ModuleMethods;
import nl.hsleiden.app.interfaces.enums.ModuleType;
import nl.hsleiden.app.modules.product.dao.ProductDao;
import nl.hsleiden.app.modules.product.resources.ProductResource;
import org.skife.jdbi.v2.DBI;

public class ProductModule implements ModuleMethods {
    public static final ModuleType MODULE_TYPE = ModuleType.PRODUCT;
    public static ProductDao productDao;

    public ProductModule(DBI jdbi) {
        ProductModule.productDao = jdbi.onDemand(ProductDao.class);
    }

    @Override
    public void registerModuleResources(Environment environment) {
        environment.jersey().register(
                new ProductResource()
        );
    }

    public static ProductDao getDao() {
        return productDao;
    }
}
