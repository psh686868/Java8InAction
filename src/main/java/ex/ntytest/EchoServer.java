package ex.ntytest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EchoServer {
    private final int port = 9999;

    public EchoServer(int port) {
        //this.port = port;
    }
    public EchoServer() {
        //this.port = port;
    }

    public static void main(String... args) throws Exception {
        //启动服务器
       /* if(args.length!=1){
            System.err.println("Usage: " + EchoServer.class.getSimpleName() +
                    " <port>");
        }
        int port = Integer.parseInt(args[0]);*/
        //EchoServer echoServer = new EchoServer();


        Executor executor = Executors.newFixedThreadPool(5);

        ServerSocket serverSocket = new ServerSocket(8899);
        Socket socket = serverSocket.accept();
        int i = 0;
        while ((socket = serverSocket.accept()) != null) {
            ++i;
            System.out.println("i:" + i);
            final int j = i;
//            InputStream inputStream = socket.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
//            StringBuffer stringBuffer = new StringBuffer();
//            String line = null;
//            while (null != (line = bufferedReader.readLine())){
//                stringBuffer.append(line + "\n\r");
//            }
            final Socket other = socket;
            executor.execute(() ->{
                Response response = null;
                try {
                    System.out.println("j: " + j);
                    response = new Response(other.getOutputStream());
                    response.htmlContent("hello word");
                    response.pushToClient(200);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });


        }


    }

    /**
     * 响应客户端信息
     * @param outputStream
     * @param string
     */
    private static void writeMsgToClient(OutputStream outputStream, String string) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        writer.append("I am server message!!!");
        writer.flush();
        writer.close();
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


    public static class Response {

        private static final String ENTER = "\r\n";
        private static final String SPACE = " ";
        //存储头信息
        private StringBuilder headerInfo ;
        //2、存储正文信息
        private StringBuilder textContent;
        //3、记录正文信息长度
        private int contentLength ;

        //4、构建输出流
        private BufferedWriter bw ;

        public Response() {
            headerInfo = new StringBuilder();
            textContent =  new StringBuilder();
            contentLength = 0;
        }

        public Response(OutputStream os) {
            this();
            bw = new BufferedWriter(new OutputStreamWriter(os));
        }

        /**
         * 创建头部信息 html报文
         * @param code
         */
        private void createHeader(int code){
            headerInfo.append("HTTP/1.1").append(SPACE).append(code).append(SPACE);
            switch (code) {
                case 200:
                    headerInfo.append("OK").append(ENTER);
                    break;
                case 404:
                    headerInfo.append("NOT FOUND").append(ENTER);
                    break;
                case 500:
                    headerInfo.append("SERVER ERROR").append(ENTER);
                    break;
                default:
                    break;
            }
            headerInfo.append("Server:myServer").append(SPACE).append("0.0.1v").append(ENTER);
            headerInfo.append("Date:Sat,"+SPACE).append(new Date()).append(ENTER);
            headerInfo.append("Content-Type:text/html;charset=UTF-8").append(ENTER);
            headerInfo.append("Content-Length:").append(contentLength).append(ENTER);
            headerInfo.append(ENTER);
        }
        /**
         * 响应给浏览器解析的内容（html正文）
         * @param content
         * @return
         */
        public Response htmlContent(String content){
            textContent.append(content).append(ENTER);
            contentLength += (content+ENTER).toString().getBytes().length;
            return this;
        }
        /**
         * 发送给浏览器端
         * @param code
         */
        public void pushToClient(int code){
            createHeader(code);
            try {
                bw.append(headerInfo.toString());
                bw.append(textContent.toString());
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
