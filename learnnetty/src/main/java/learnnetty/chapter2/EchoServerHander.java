package learnnetty.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.time.LocalTime;

/**
 * 服务端的处理器
 * Create by psh
 * Date: 2017/12/7
 */
public class EchoServerHander extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(" server received from client message : " + in.toString(CharsetUtil.UTF_8));
        //将接收到的消息写给发送者，而不冲刷出站消息
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelReadComplete method 调用");
        //将未决消息冲刷到远程节点，并且关闭该 Channel
        LocalTime now = LocalTime.now();
        System.out.println(now);
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        //关闭该channel
        ctx.close();
    }
}
