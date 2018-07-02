package ex.ntytest.nio;

import io.netty.util.CharsetUtil;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author PSH
 * Date: 2018/6/11
 */
public class NioClient {


    public static void start() throws Exception{
        try {
            SocketChannel socketChannel = SocketChannel.open();
            boolean flag = socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
            String res = "hello service";
            System.out.println("发送-：" + res);
            byte[] bs1 = res.getBytes(CharsetUtil.UTF_8);
            ByteBuffer writeBuffer = ByteBuffer.wrap(bs1);
            socketChannel.write(writeBuffer);
            ByteBuffer buffer = ByteBuffer.allocate(100);
            socketChannel.read(buffer);
            byte[] data = buffer.array();
            String message = new String(data);
            System.out.println("recevie message from server:, size:" + buffer.position() + " msg: " + message);
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String waterMarkImg = "fda//fd/sa.jpg.http://https://";

        if (waterMarkImg.startsWith("http://")) {
            waterMarkImg = waterMarkImg.replaceFirst("http://", "");
        } else if (waterMarkImg.startsWith("https://")) {
            waterMarkImg = waterMarkImg.replaceFirst("https://", "");
        } else if (waterMarkImg.startsWith("//")) {
            waterMarkImg = waterMarkImg.replaceFirst("//", "");
        } else if (waterMarkImg.startsWith("/")) {
            waterMarkImg = waterMarkImg.replaceFirst("/", "");
        }
        System.out.println("waterMarkImg = " + waterMarkImg);
//        try {
//            start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
