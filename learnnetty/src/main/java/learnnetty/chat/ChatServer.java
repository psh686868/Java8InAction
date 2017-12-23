package learnnetty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;
import lombok.extern.slf4j.Slf4j;


/**
 * Create by psh
 * Date: 2017/12/22
 *
 * 聊天程序启动
 */
@Slf4j
public class ChatServer {
    private String defaultAdd = "127.0.0.1";
    private int defaultPort = 8888;


    private final EventLoopGroup bossGroup;

    private final EventLoopGroup wokerGroup;

    private final ChannelGroup channelGroup;


    private Channel channel;

    public ChatServer () {
        bossGroup = new NioEventLoopGroup();
        wokerGroup = new NioEventLoopGroup();
        channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
        init();
    }

    public void init () {
        ServerBootstrap bootstrap = new ServerBootstrap();
        this.start(bootstrap);
    }

    public void start (ServerBootstrap bootstrap) {
        bootstrap.group(bossGroup,wokerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChatServerInitalizer(channelGroup));
        try {
            channel = bootstrap.bind(defaultAdd, defaultPort).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
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


}
