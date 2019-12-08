package nl.hsleiden.app.services;

import nl.hsleiden.app.daos.DAO;
import nl.hsleiden.app.models.Product;

import java.util.List;

public class ProductService {
    private static DAO productDao;

    public ProductService(DAO productDao) {
        ProductService.productDao = productDao;
    }


    /**
     * @author Jesse Minneboo
     * @return product DAO
     */
    public List<Product> getLatestProducts() {
        return productDao.getLatersProductsFromDatabase();
    }


    /**
     * @author Jesse Minneboo
     * @param productName product name
     * @param productDescription product description
     * @param productPrice product price
     * @param productImagePath product image path to image
     * @return product DAO
     */
    public Product addNewProduct(
            String productName,
            String productDescription,
            double productPrice,
            String productImagePath
    ) {
        long productId = productDao.insertProductIntoDatabase(productName, productDescription, productPrice, productImagePath);
        Product newProduct = productDao.findProductByIdInDatabase(productId);
        return newProduct;
    }


    /**
     * @author Jesse Minneboo
     * @return product dao
     */
    public List<Product> getAllProducts() {
        return productDao.getAllProductsFromDatabase();
    }


    /**
     * @author Jesse Minneboo
     * @return product DAO
     */
    public List<Product> getAllProducts(int limit) {
        return productDao.getAllProductsFromDatabase(limit);
    }
}
