package ex.ntytest.express.chapter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


/**
 * Create by psh
 * Date: 2017/12/7
 * netty的学习 客户
 */
@ChannelHandler.Sharable //标记实列可以被多个Channel共享
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("client received message from server: "+msg.toString(CharsetUtil.UTF_8));
    }

    // 当channel 被激活的时候回被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("初始化");
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty client rocks",CharsetUtil.UTF_8));
    }

    // 发生异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();//After it is closed it is not possible to reuse it again.
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Thread.sleep(5000);
        System.out.println("client handlerRemoved method invoke ");

        Thread.sleep(10000);
    }
}
