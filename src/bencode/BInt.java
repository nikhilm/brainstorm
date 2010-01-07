package bencode;

/**
 * Represents a long bencoded integer.
 */
public class BInt extends BType {
    private long _value;
    public BInt( long n ) {
        _value = n;
        _type = Type.INT;
    }

    public Long value() {
        return _value;
    }

    public String encode() {
        return "i"+_value+"e";
    }

    public String prettyPrint() {
        return String.valueOf(value());
    }
}
