package ex.ntytest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


@ChannelHandler.Sharable // 标示一个 ChannelHandler 可以被多个 Channel 安全地共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received:"+in.toString(CharsetUtil.UTF_8));//记录消息到控制台
        ctx.write(in);
        throw new RuntimeException("看是否会调用异常的exceptionCaught");

    }

    // channel 读完事件
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);//将未决消息冲刷到远程节点，并且关闭该 Channel
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();//打印异常栈轨迹
        ctx.close();//关闭该Channel
    }


}

