package nl.hsleiden.app.interfaces.abstracts;

public abstract class UploadPath {
    public static final String PRODUCT_PATH = "src/main/resources/uploads/products/";

    public static String generateProjectPath(String filename) {
        return PRODUCT_PATH + filename;
    }
}
