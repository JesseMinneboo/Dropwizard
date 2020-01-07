package nl.hsleiden.app.daos;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface AdminDao {

    @SqlQuery("SELECT counter FROM admin_website_searches")
    long getWebsiteSearchesCounter();

    @SqlUpdate("UPDATE admin_website_searches SET counter = :counter_now + 1")
    int addToSearchesCounter(@Bind("counter_now") long counterNow);
}
