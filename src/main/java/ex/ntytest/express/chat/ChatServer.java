package ex.ntytest.express.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;


/**
 * Create by psh
 * Date: 2017/12/22
 *
 * 聊天程序启动
 */
@Slf4j
public class ChatServer {
    private String defaultAdd = "127.0.0.1";
    private int defaultPort = 8282;


    private final EventLoopGroup bossGroup;

    private final EventLoopGroup wokerGroup;

    private final ChannelGroup channelGroup;

    private final ServerBootstrap bootstrap ;


    private Channel channel;

    public ChatServer () {
        bossGroup = new NioEventLoopGroup();
        wokerGroup = new NioEventLoopGroup();
        channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
        bootstrap = new ServerBootstrap();
    }

    public void start () {
        this.start(this.defaultAdd,this.defaultPort);
    }

    public void start (int port) {
        this.start(this.defaultAdd,port);
    }

    public void start (String addRess,int port) {
        bootstrap.group(this.bossGroup,this.wokerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChatServerInitalizer(channelGroup));
        try {
            channel = bootstrap.bind(defaultAdd, defaultPort).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.info("聊天程序启动失败:",e);
        }
    }


    //停止聊天 释放服务
    public void destory () {
        if (channel != null) {
            channel.close();
        }

        channelGroup.close();

        bossGroup.shutdownGracefully();

        wokerGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
        int i = Runtime.getRuntime().availableProcessors();

        System.out.println(i);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("shutdown");
            chatServer.destory();
        }));

    }

}
