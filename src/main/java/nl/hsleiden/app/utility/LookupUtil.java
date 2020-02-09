package nl.hsleiden.app.utility;

public class LookupUtil {
    public static <E extends Enum<E>> boolean lookup(Class<E> e, String id) {
        try {
            Enum.valueOf(e, id);

            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
