package brainstorm;

import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.channels.spi.*;

public class Server implements Runnable {
    private InetAddress host;
    private int port;

    private ServerSocketChannel serverChannel;

    private Selector selector;

    public Server(InetAddress h, int p) throws IOException {
        host = h;
        port = p;
        selector = initSelector();
    }

    private Selector initSelector() throws IOException {
        Selector socketSelector = SelectorProvider.provider().openSelector();

        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        InetSocketAddress isa = new InetSocketAddress(host, port);
        serverChannel.socket().bind(isa);

        serverChannel.register(socketSelector, SelectionKey.OP_ACCEPT);

        return socketSelector;
    }

    public void run() {
        while( true ) {
            try {
                this.selector.select();

                Iterator it = this.selector.selectedKeys().iterator();
                while( it.hasNext() ) {
                    SelectionKey k = (SelectionKey) it.next();
                    it.remove();

                    if( !k.isValid() ) {
                        continue;
                    }

                    if( k.isAcceptable() ) {
                        accept(k);
                    }
                }
            } catch( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey k) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) k.channel();

        SocketChannel socketChannel = serverSocketChannel.accept();
        Socket sock = socketChannel.socket();
        System.out.println("Accepted " + sock);
        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}
