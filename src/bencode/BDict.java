package bencode;
import java.util.HashMap;

public class BDict extends BType {
    private HashMap<BString,BType> _value;

    public BDict() {
        _value = new HashMap<BString,BType>();
        _type = Type.DICT;
    }

    public void add( String k, BType v ) {
        add( new BString(k), v );
    }

    public void add( BString k, BType v ) {
        _value.put( k, v );
    }

    public boolean contains( BString k ) {
        return _value.containsKey(k);
    }

    public boolean contains( String k ) {
        return contains( new BString(k) );
    }

    public BType get( BString k ) {
        return _value.get(k);
    }

    public BType get( String k ) {
        return get( new BString(k) );
    }

    public HashMap<BString,BType> value() {
        return _value;
    }

    public String encode() {
        StringBuilder b = new StringBuilder("d");

        for( BString k : _value.keySet() ) {
            b.append( k.encode() );
            b.append( _value.get(k).encode() );
        }

        b.append("e");
        return b.toString();
    }
}
