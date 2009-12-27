package brainstorm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatWidget extends Box {
    private JTextArea chatArea;
    private JTextField sendField;
    public ChatWidget() {
        super(BoxLayout.Y_AXIS);
        setupGUI();
    }

    private void setupGUI() {
        chatArea = new JTextArea( "Chat" );
        chatArea.setPreferredSize( new Dimension( 200, 400 ) );
        chatArea.setEditable( false );

        sendField = new JTextField( "What");

        add( new JScrollPane( chatArea ) );
        add( createVerticalStrut( 5 ) );
        add( new JScrollPane( sendField ) );

        sendField.grabFocus();

        setSize(200, 600);
        setVisible(true);
    }
}
