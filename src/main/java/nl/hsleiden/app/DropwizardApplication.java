package nl.hsleiden.app;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.hsleiden.app.checks.DatabaseHealthCheck;
import nl.hsleiden.app.daos.DAO;
import nl.hsleiden.app.resources.HtmlPageResource;
import nl.hsleiden.app.resources.UserResource;
import nl.hsleiden.app.services.UserService;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.HashMap;

public class DropwizardApplication extends Application<DropwizardConfiguration> {
    final static String API_VERSION = "V0.1";

    public static void main(String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    public void run(DropwizardConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final DAO dao = jdbi.onDemand(DAO.class);

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // user path
        environment.jersey().register(new UserResource(
                new UserService(dao)));

        // index.html
        environment.jersey().register(
                new HtmlPageResource()
        );

        // Database health check
        environment.healthChecks().register("checks",
                new DatabaseHealthCheck(jdbi, configuration.getDataSourceFactory().getValidationQuery()));
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<DropwizardConfiguration>() {
            public PooledDataSourceFactory getDataSourceFactory(DropwizardConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(new MultiPartBundle());
    }

    public static HashMap<String, String> getVersion() {
        HashMap<String, String> version = new HashMap<>();
        version.put("version", API_VERSION);

        return version;
    }
}
