package bencode;
import java.util.ArrayList;

public class BList extends BType {
    private ArrayList<BType> _value;

    public BList() {
        _value = new ArrayList<BType>();
        _type = Type.LIST;
    }

    public void add( BType t ) {
        _value.add( t );
    }

    public ArrayList<BType> value() {
        return _value;
    }

    public String encode() {
        StringBuilder b = new StringBuilder("l");

        for( BType item : _value ) {
            b.append( item.encode() );
        }

        b.append("e");
        return b.toString();
    }

    public String prettyPrint() {
        StringBuilder b = new StringBuilder("[ ");

        for( BType item : _value ) {
            if( item != _value.get(0) )
                b.append(", ");
            b.append( item.prettyPrint() );
        }

        b.append(" ]");
        return b.toString();
    }
}
