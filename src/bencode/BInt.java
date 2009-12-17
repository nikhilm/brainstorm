package bencode;

/**
 * Represents a 32-bit bencoded integer.
 */
public class BInt extends BType {
    private int _value;
    public BInt( int n ) {
        _value = n;
        _type = Type.INT;
    }

    public Integer value() {
        return _value;
    }

    public String encode() {
        return "i"+_value+"e";
    }
}
