package ex.ntytest.nio;

import io.netty.util.CharsetUtil;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author PSH
 * Date: 2018/6/11
 */
@Slf4j
public class NioServer {

    private static final int DEFALUT_PORT = 8000;
    private static final String LOCAL_ADD = "127.0.0.1";

    private Selector selector;
    ExecutorService executorService;

    NioServer() {
        this(NioServer.DEFALUT_PORT, NioServer.LOCAL_ADD);
    }

    NioServer(final int port, final String address) {
        init(port, address);
    }

    private void init(int port, String address) {
        try {
            selector = Selector.open();

            ServerSocketChannel serverSocketChannel = initServerSocketChannel(port);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            executorService = Executors.newCachedThreadPool();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // init ServerSocketChannel
    private ServerSocketChannel initServerSocketChannel(final int port) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        return serverSocketChannel;
    }

    public void listen() {
        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                        final ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel socketChanel = server.accept();
                        System.out.println(
                            "客户端机子的地址是 " + socketChanel.socket().getRemoteSocketAddress() + "  客户端机机子的端口号是 " + socketChanel.socket()
                                .getLocalPort());

                        socketChanel.configureBlocking(false);

                        SelectionKey clientKey = socketChanel.register(selector, SelectionKey.OP_READ);
                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        clientKey.attach(buffer);


                        // 处理 accept 事件
                    } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                        // 处理 read 事件
                        recive(key);
                    } else if ((key.readyOps() & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE) {
                        // 处理 write 事件

                        System.out.println("aaa");
                    }

                    iterator.remove();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void recive(SelectionKey key) {
        SocketChannel sc = (SocketChannel) key.channel();
        try {
            ByteBuffer dst = ByteBuffer.allocate(5 * 1024);//5K
            dst.clear();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            readBuffer.clear();
            while (true) {
                int bytesRead = sc.read(readBuffer);
                if (bytesRead > 0) {
                    readBuffer.flip();
                    dst.put(readBuffer);
                    readBuffer.clear();
                } else {
                    break;
                }
            }
            dst.flip();
            String request = new String(dst.array());
            System.out.println("接收数据：" + request);
            String response = "hello word";


            ByteBuffer writeBuffer = ByteBuffer.wrap(response.getBytes(CharsetUtil.UTF_8));

            sc.write(writeBuffer);
            key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NioServer server = new NioServer();
        server.listen();
    }
}