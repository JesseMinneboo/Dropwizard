package nl.hsleiden.app;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.hsleiden.app.checks.DatabaseHealthCheck;
import nl.hsleiden.app.filters.AuthFilter;
import nl.hsleiden.app.modules.game.GameModule;
import nl.hsleiden.app.modules.user.UserModule;
import nl.hsleiden.app.modules.user.dao.seeders.UserTableSeeder;
import nl.hsleiden.app.providers.TokenProvider;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.skife.jdbi.v2.DBI;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;


/**
 * @author Jesse Minneboo
 */
public class MainApplication extends Application<MainConfiguration> {
    public static TokenProvider tokenProvider;

    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<MainConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<MainConfiguration>() {
            public PooledDataSourceFactory getDataSourceFactory(MainConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(new MultiPartBundle());
    }

    public void run(MainConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");


        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,Authorization,auth");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(MultiPartFeature.class);

        new UserModule(jdbi).registerModuleResources(environment);
        new GameModule(jdbi).registerModuleResources(environment);

        // filters
        environment.jersey().register(new AuthFilter());


        // Database health check
        environment.healthChecks().register("checks",
                new DatabaseHealthCheck(jdbi, configuration.getDataSourceFactory().getValidationQuery()));

        // jwt token
        tokenProvider = new TokenProvider();

        new UserTableSeeder(true);
    }
}
