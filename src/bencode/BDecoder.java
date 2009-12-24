// Created: Thu Dec 17 2009
package bencode;
import java.io.*;

/**
 * Implements the bencode protocol, originally introduced in Bittorrent.
 *
 * The implementation is complete, except for arbitrary length integers.
 * Currently it only implements int ( 32-bit ) integer support.
 *
 * @author Nikhil Marathe (nsm.nikhil@gmail.com)
 * @version 0.1
 *
 * <code>
 * import bencode.*;
 *
 * BDecoder dec = new BDecoder( [string to decode] );
 * BType t = dec.parse();
 * System.out.println( t.value() );
 *
 * </code>
 *
 * @see BInt
 * @see BString
 * @see BList
 * @see BDict
 */
public class BDecoder {
    String text;
    int pos;

    public BDecoder( String s ) {
        text = s;
        pos = 0;
    }

    public BType parse() throws BException {
        BType result = null;
        char a = text.charAt(pos);
        switch( a ) {
            case 'i':
                result = parseInt();
                break;
            case 'l':
                result = parseList();
                break;
            case 'd':
                result = parseDict();
                break;
            default:
                result = parseString();
                break;
        }
        return result;
    }

    private BString parseString() throws BException {
        int cpos = pos;
        while( text.charAt(cpos) != ':' ) {
            cpos++;
            if( cpos >= text.length() ) {
                throw new BException("Invalid string. No delimiter ':' found at " + text.substring(pos));
            }
        }

        int length = Integer.parseInt( text.substring( pos, cpos ) );
        if( length <= 0 ) {
            throw new BException("String length must be positive");
        }

        pos = cpos+1;

        String s = text.substring( pos, pos+length ); 
        
        pos += length;

        return new BString( s );
    }

    private BDict parseDict() throws BException {
        pos += expect('d');

        BDict dict = new BDict();
        while( c() != 'e' ) {
            try {
                BString key = (BString) parse();
                BType value = parse();
                dict.add( key, value );
            } catch(ClassCastException cce) {
                throw new BException("Only strings are allowed as dictionary keys");
            }
        }

        pos++;
        return dict;
    }

    private BList parseList() throws BException {
        pos += expect('l');

        BList list = new BList();
        while( c() != 'e' ) {
            list.add( parse() );
        }

        pos++;
        return list;
    }

    private BInt parseInt() throws BException {
        int n = 0;
        boolean neg = false;

        pos += expect('i');
        if( c() == '-' ) {
            neg = true;
            pos++;
        }

        String num = "";
        while( c() != 'e' ) {
            num += c();
            pos++;
        }

        if( num == "0" && neg )
            throw new BException("Invalid integer -0");

        if( num != "0" && num.charAt(0) == '0' )
            throw new BException("Invalid integer " + num + " Numbers have to be base 10" );

        if( neg )
            num = "-" + num;

        pos++;
        return new BInt( Long.parseLong(num) );
    }

    private char c() {
        return text.charAt(pos);
    }

    private int expect( char ch ) {
        if( c() != ch )
            throw new BException( String.format( "Expected %c at %s", ch, text.substring(pos) ) );
        return 1;
    }
}
