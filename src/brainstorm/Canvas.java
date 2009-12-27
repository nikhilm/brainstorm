package brainstorm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Canvas extends JPanel implements Runnable {
    Image image;
    Graphics2D gfx;
    Dimension size;

    public Canvas( Dimension size ) {
        super();
        setSize( size );
        this.size = size;
    }

    public void paintComponent( Graphics g ) {
        super.paintComponent( g );

        if( image == null ) {
            image = createImage( (int)size.getWidth(), (int)size.getHeight() );
            gfx = (Graphics2D)image.getGraphics();
        }

        gfx.drawString("Hi there", 40, 40);
        g.drawImage(image, 0, 0, null);
    }

    public void run() {
        // TODO read from message queue and draw stuff
    }
}
