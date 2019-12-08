package nl.hsleiden.app.daos.product;

import nl.hsleiden.app.models.Product;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ResultSetMapper<Product> {

    @Override
    public Product map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Product(
                resultSet.getLong("product_id"),
                resultSet.getString("product_name"),
                resultSet.getString("product_description"),
                resultSet.getDouble("product_price"),
                resultSet.getString("product_image_path")
        );
    }
}
