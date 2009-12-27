package brainstorm;

import java.util.concurrent.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import bencode.*;

public class Brainstorm extends JFrame {
    private JToolBar toolbar;

    private Action pauseAction;
    private Action lineAction;
    private Action rectAction;

    private ChatWidget chat;
    private StartWidget menu;

    private Canvas canvas;

    Thread serverThread;

    // Message Queue
    BlockingQueue<BDict> mq;

    public Brainstorm() {
        super();
        setTitle("Brainstorm");

        setupGUI();

        serverThread = null;
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
        menu.setVisible(false);
        remove( menu );

        if( mq == null ) {
            mq = new LinkedBlockingQueue<BDict>();
        }
        // new Client blah blah

        chat.setVisible( true );
        toolbar.setVisible( true );

        // actually the canvas will be created on the first read when we get the size from the server
        canvas = new Canvas(new Dimension(640, 480));
        add(canvas);
        validate();
    }

    protected void hostServer( String nick, int w, int h ) {
        // start a server
        // and then call joinServer with ( localhost, nick )
        initServerThread();
        joinServer("127.0.0.1", nick);
    }

    private void initServerThread() {
        try {
            if( serverThread == null ) {
                serverThread = new Thread( new Server( InetAddress.getByName("127.0.0.1"), 7000 ) );
            }
            serverThread.start();
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

}
