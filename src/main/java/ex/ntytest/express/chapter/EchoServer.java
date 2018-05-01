package ex.ntytest.express.chapter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * netty的服务端的练习
 * @author PSH
 * Date: 2017/12/7
 */
public class EchoServer {
    private final int port ;

    EchoServer (int port) {
        this.port = port;
    }

    public void start () throws Exception {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    .localAddress(new InetSocketAddress(port))//使用指定的端口设置套接字地址
                    .channel(NioServerSocketChannel.class)// 指定所使用的 NIO 传输 Channel
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //添加一个EchoServerHandler到于Channel的 ChannelPipeline
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast( new EchoServerHander());
                        }
                    });
            ChannelFuture future = serverBootstrap.bind().sync();
            System.out.println(EchoServer.class.getName() +
                    " !!!!!! started and listening for connections on " + future.channel().localAddress());
            //(7) 获取 Channel 的CloseFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
            System.out.println("server is closed");
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args)  {
        EchoServer echoServer = new EchoServer(9999);
        try {
            echoServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
