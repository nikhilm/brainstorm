/*
 * Copyright (c) 2009 Nikhil Marathe <nsm.nikhil@gmail.com>
 * Licensed under the GNU General Public License. See LICENSE for details.
 *
 * Created December 24, 2009
 */
package brainstorm.messages;
import bencode.*;

public abstract class Message {
    public Message(String bencs) {
        benc = (BDict)(new BDecoder(bencs)).parse();
        validate();
    }

    private void validate() throws RuntimeException {
        assert Type.values().contains((Integer)((BInt)benc.get("type")).value());
    }

    enum Type {
        PROTOCOL,
        SHAPE,
        CHAT
    }
    private Type type;
    public Type getType() { return this.type; }
    public void setType( Type _var ) { this.type = _var; }

    private string nick;
    public string getNick() { return this.nick; }
    public void setNick( string _var ) { this.nick = _var; }

    private long servertime;
    public long getServertime() { return this.servertime; }
    public void setServertime( long _var ) { this.servertime = _var; }

    private BDict benc;
}
