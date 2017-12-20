package ex.ntytest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port = 9999;

    public EchoServer(int port) {
        //this.port = port;
    }
    public EchoServer() {
        //this.port = port;
    }

    public static void main(String... args) {
        //启动服务器
       /* if(args.length!=1){
            System.err.println("Usage: " + EchoServer.class.getSimpleName() +
                    " <port>");
        }
        int port = Integer.parseInt(args[0]);*/
        EchoServer echoServer = new EchoServer();
        while (true){

        }

    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();//创建 EventLoopGroup 即事件监听器循环器
        try {
            ServerBootstrap b = new ServerBootstrap();//创建ServerBootstrap
            b.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))//使用指定的 端口设置套接字地址
                    .childHandler(new ChannelInitializer<SocketChannel>(){//添加一个 EchoServerHandler 到子 Channel的 ChannelPipeline
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });

            ChannelFuture channelFuture = b.bind().sync();//异步地绑定服务器；调用 sync()方法阻塞等待直到绑定完成
            channelFuture.channel().closeFuture().sync();//获取 Channel 的CloseFuture，并且 阻 塞 当 前 线关闭 EventLoopGroup， 程直到它完成

        }finally {
            eventLoopGroup.shutdownGracefully().sync();//关闭 EventLoopGroup， 程直到它完成释放所有的资源
        }


    }
}
