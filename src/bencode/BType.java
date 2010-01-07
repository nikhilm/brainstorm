package bencode;

/**
 * Superclass for all bencode protocol types.
 *
 * You usually aren't interested in this
 * Rather you'll downcast the type to one of
 * the concrete types, or simply call value()
 *
 * @author Nikhil Marathe
 *
 */
public abstract class BType {
    public enum Type {
        INT,
        STRING,
        LIST,
        DICT
    }
    Type _type;

    /**
     * @return the value ( ie. the data ) contained in the type.
     */
    public abstract Object value();
    /**
     * @return bencoded version of the type.
     */
    public abstract String encode();

    public Type type() {
        return _type;
    }

    public abstract String prettyPrint();
}
