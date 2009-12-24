package brainstorm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartWidget extends Box {
    private JPanel chatArea;
    private JTextArea sendArea;

    private Brainstorm brain;
    public StartWidget(Brainstorm b) {
        super(BoxLayout.Y_AXIS);

        brain = b;
        setupGUI();
    }

    private void setupGUI() {
        JButton hostButton = new JButton("Host a server...");
        hostButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showHostPage();
            }
        });
        add( hostButton );

        JButton joinButton = new JButton("Join a server...");
        joinButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showJoinPage();
            }
        });
        add( joinButton );

        setAlignmentX( Box.CENTER_ALIGNMENT );
        setVisible(true);
    }

    private void showHostPage() {
        removeAll();

        final JTextField ip = new JTextField("<ip address>");
        add(ip);

        final JTextField nick = new JTextField("<nickname>");
        add(nick);

        JButton join = new JButton("Join");
        join.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                // TODO validation
                brain.joinServer( ip.getText(), nick.getText() );
            }
        });
        add(join);
    }

    private void showJoinPage() {
        
        System.out.println("Called");
        //removeAll();

        final JTextField nick = new JTextField("<nickname>");
        add(nick);

        JButton host = new JButton("Host");
        host.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                // TODO validation
                brain.hostServer( nick.getText() );
            }
        });
        add(host);
    }
}
