package ex.ntytest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;


public class EchoClient {
    private final String host = "127.0.0.1";
    private final int port = 9999;

    public EchoClient(String host, int port) {
        //this.host = host;
        //this.port = port;
    }

    public EchoClient() {

    }

    public static void main(String[] args) throws Exception {
        /*if (args.length != 2) {
            System.err.println(
                    "Usage: " + EchoClient.class.getSimpleName() +
                            " <host> <port>");
            return;
        }
        String host = args[0];*/
        //int port = Integer.parseInt();
        //new EchoClient(host, port).start();
    }

    public void start() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {

            bootstrap.group(eventLoopGroup)//指定 EventLoopGroup 以 处理客户端事件；需要适 用于 NIO 的实现
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))//
                    .handler(new ChannelInitializer<SocketChannel>() {//在创建Channel时，向 ChannelPipeline中添加一个 EchoClientHandler 实例
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();//连接到远程节点，阻塞等待直到连接完成
            channelFuture.channel().closeFuture().sync();//阻塞，直到Channel 关闭

        }finally {
            eventLoopGroup.shutdownGracefully().sync();//关闭线程池并且释放所有的资源
        }
    }
}
