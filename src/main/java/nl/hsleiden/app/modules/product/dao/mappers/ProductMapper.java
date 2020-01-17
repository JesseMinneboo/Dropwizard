package nl.hsleiden.app.modules.product.dao.mappers;

import nl.hsleiden.app.modules.product.models.Product;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductMapper implements ResultSetMapper<Product> {

    @Override
    public Product map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getString("image_path"),
                resultSet.getLong("counter")
        );
    }
}
