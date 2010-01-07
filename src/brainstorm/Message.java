/*
 * Copyright (c) 2009 Nikhil Marathe <nsm.nikhil@gmail.com>
 * Licensed under the GNU General Public License. See LICENSE for details.
 *
 * Created December 24, 2009
 */
package brainstorm;
import java.nio.*;
import java.nio.charset.*;
import java.util.*;
import bencode.*;

public class Message {

    BDict dict;
    private Message(BDict d) {
        dict = d;
    }

    public void setServerTime( long time ) {
        dict.add( "server_time", new BInt( time ) );
    }

    public long getServerTime() {
        return ((BInt)dict.get("server_time")).value();
    }

    public String toString() {
        return "Message: " + dict.prettyPrint();
    }

    public static Message decode( ByteBuffer buffer, int count ) {

        String msg = new String( buffer.array(), 0, count );
        System.err.println("Message is " + msg);
        String[] p = msg.trim().split(":", 2); // not more than two pieces
        System.err.println(p[0] + ", " + p[1]);
        if( p.length != 2 ) {
            throw new MessageParseException("Error reading data");
        }
        
        int len = Integer.parseInt(p[0]);
        if( len != p[1].length() ) {
            throw new MessageParseException("Not enough data. Expected " + len + " got " + p[1].length());
        }

        return new Message( (BDict)( new BDecoder(p[1]).parse()));

    }

    public ByteBuffer encode() {
        String s = dict.encode();
        s = String.valueOf( s.length() ) + ":" + s;

        byte[] bs = s.getBytes(Charset.forName("ISO-8859-1"));
        return ByteBuffer.wrap(bs);
    }

}
