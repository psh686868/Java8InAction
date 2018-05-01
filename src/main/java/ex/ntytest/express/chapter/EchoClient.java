package ex.ntytest.express.chapter;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 客户端的启动类
 * Create by psh
 * Date: 2017/12/7
 */
public class EchoClient {
    private final String ipAdress;
    private final   int port;

    public EchoClient ( String ipAdress , int port) {
        this.ipAdress = ipAdress;
        this.port = port;
    }

    public void start () throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .remoteAddress(new InetSocketAddress(ipAdress,port))
                    //适用于 NIO 传输的Channel 类型
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel (SocketChannel socketChannel) throws Exception{
                           // 在创建Channel时，向 ChannelPipeline中添加一个 EchoClientHandler实例
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //连接到远程节点，阻塞等待直到连接完成
            ChannelFuture channelFuture = bootstrap.connect().sync();

            System.out.println("1");
            channelFuture.channel().closeFuture().sync();
            System.out.println("2");
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient("127.0.0.1",9999);
        try {
            echoClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
