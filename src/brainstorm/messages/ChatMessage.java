/*
 * Copyright (c) 2009 Nikhil Marathe <nsm.nikhil@gmail.com>
 * Licensed under the GNU General Public License. See LICENSE for details.
 *
 * Created December 24, 2009
 */
package brainstorm.messages;

public class ChatMessage extends Message {
    public ChatMessage() {
    }

    private Type type = Type.CHAT;
    public Type getType() { return this.type; }
    public void setType( Type _var ) { this.type = _var; }

    private string text;
    public string getText() { return this.text; }
    public void setText( string _var ) { this.text = _var; }

}
