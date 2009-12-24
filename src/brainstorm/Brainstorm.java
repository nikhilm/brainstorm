package brainstorm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Brainstorm extends JFrame {
    private JToolBar toolbar;

    private Action pauseAction;
    private Action lineAction;
    private Action rectAction;

    private ChatWidget chat;
    private StartWidget menu;

    public Brainstorm() {
        super();
        setTitle("Brainstorm");

        setupGUI();

        // switch on only when we are connected
        toolbar.setVisible( false );
        chat.setVisible( false );
    }

    private void setupGUI() {
        Container c = getContentPane();

        setupActions();

        toolbar = new JToolBar();
        toolbar.setOrientation( JToolBar.VERTICAL );

        toolbar.add(lineAction);
        toolbar.add(rectAction);
        toolbar.addSeparator( new Dimension(0, 50) );
        toolbar.add(pauseAction);

        c.add( toolbar, BorderLayout.WEST );

        chat = new ChatWidget();
        c.add( chat, BorderLayout.EAST );

        menu = new StartWidget(this);
        c.add( menu, BorderLayout.CENTER );

        setSize( 800, 600 );
        setVisible( true );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

    }

    private void setupActions() {
        pauseAction = new PauseAction();
        lineAction = new LineAction();
        rectAction = new RectAction();
    }

    public static void main(String[] args) {
        new Brainstorm();
    }

    public class PauseAction extends AbstractAction {
        public PauseAction() {
            super( "Pause", new ImageIcon("brainstorm/icons/pause.png") );
        }

        public void actionPerformed(ActionEvent e) {
        }
    }

    public class LineAction extends AbstractAction {
        public LineAction() {
            super( "Draw Line", new ImageIcon("brainstorm/icons/tool_line.png") );
        }

        public void actionPerformed(ActionEvent e) {
        }
    }

    public class RectAction extends AbstractAction {
        public RectAction() {
            super( "Draw Rectangle", new ImageIcon("brainstorm/icons/tool_rectangle.png") );
        }

        public void actionPerformed(ActionEvent e) {
        }
    }

    protected void joinServer( String ip, String nick ) {
        // create a new thread with socket
        // or perhaps just NIO
        // and register events
        System.out.println("Called");
        remove( menu );

        chat.setVisible( true );
        toolbar.setVisible( true );
    }

    protected void hostServer( String nick ) {
        // start a server
        // and then call joinServer with ( localhost, nick )
    }

    public void destroy() {
    }

}
