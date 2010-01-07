package bencode;
public class BString extends BType {
    private String _value;
    public BString( String s ) {
        _value = s;
        _type = Type.STRING;
    }

    public String value() {
        return _value;
    }

    public String encode() {
        if( _value == "" )
            return "";
        return String.format( "%1$d:%2$s", _value.length(), _value );
    }

    public String prettyPrint() {
        return value();
    }

    public int hashCode() {
        return _value.hashCode();
    }
}
