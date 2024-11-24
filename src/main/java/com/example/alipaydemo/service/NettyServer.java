package com.example.alipaydemo.service;

import com.example.alipaydemo.websocket.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class NettyServer {

    @Autowired
    private SslContext sslCtx;

    private static final int READ_IDLE_TIME = 60; // 秒
    private static final int WRITE_IDLE_TIME = 30; // 秒
    private static final int ALL_IDLE_TIME = 10; // 秒
    public void start(){

        //创建两个线程组boosGroup和workerGroup,含有的子线程NioEventLoop的个数默认为cpu核数的两倍
        //boosGroup只是处理链接请求,真正的和客户端业务处理,会交给workerGroup完成
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器的启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式编程来配置参数
            //设置两个线程组
            bootstrap.group(boosGroup,workerGroup)
                    //使用NioSctpServerChannel作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    //初始化服务器链接队列大小，服务端处理客户端链接请求是顺序处理的，所以同一时间只能处理一个客户端链接
                    //多个客户端同时来的时候，服务端将不能处理的客户端链接请求放在队列中等待处理
                    .option(ChannelOption.SO_BACKLOG,1024)
                    //创建通道初始化对象，设置初始化参数
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("收到到新的链接");
                            //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                            ch.pipeline().addLast(new HttpServerCodec());
                            //以块的方式来写的处理器
                            ch.pipeline().addLast(new ChunkedWriteHandler());// 处理大数据流的分块写入。这对于 HTTP 响应的流式传输很有用，可以在处理大文件传输或长时间流式传输时提高性能。
                            ch.pipeline().addLast(new HttpObjectAggregator(8192)); //用于将HTTP消息的多个部分聚合成一个完整的HTTP消息。
//                            ch.pipeline().addLast(new MessageHandler());//添加测试的聊天消息处理类
                            ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", null, true, 65536 * 10));
                            // 处理 WebSocket 协议的握手和数据帧的编解码。它接受 WebSocket 握手请求，并根据请求 URL (/ws) 处理 WebSocket 连接。
                            //参数解释：
                            //"/ws": WebSocket 的路径，客户端连接 WebSocket 时需要指定的路径。
                            //null: 不需要支持的子协议（可以指定具体的子协议）。
                            //true: 是否允许继续处理 WebSocket 握手请求（保持 true 表示允许）。
                            //65536 * 10: WebSocket 数据帧的最大消息大小，这里设置为 655,360 字节（约 640KB）。
                            ch.pipeline().addLast(new HttpRequestHandler("/ws"));
                            // 处理文本信息
//                            ch.pipeline().addLast(new TextWebSocketFrameHandler());
                            // 处理聊天文本信息
                            ch.pipeline().addLast(new ChatWebSocketHandler());
//                            ch.pipeline().addLast(new TestWebHandler());
                            // 处理文本信息
//                            ch.pipeline().addLast(new TextWebSocketFrameHandler());
                            // 处理聊天二进制信息
//                            ch.pipeline().addLast(new BinaryWebSocketFrameHandler());
                            // 添加 IdleStateHandler，设置读空闲、写空闲和读写空闲的超时时间
                            ch.pipeline().addLast(new IdleStateHandler(READ_IDLE_TIME, WRITE_IDLE_TIME, ALL_IDLE_TIME));
                            ch.pipeline().addLast(new WebSocketFrameHandler());
                            // 证书加密
                            // 添加 SSL/TLS 处理器
//                            ch.pipeline().addLast(sslCtx.newHandler(ch.alloc()));
                        }
                    });
            System.out.println("netty server start..");
            //绑定一个端口并且同步，生成一个ChannelFuture异步对象，通过isDone()等方法判断异步事件的执行情况
            //启动服务器(并绑定端口)，bind是异步操作，sync方法是等待异步操作执行完毕
            ChannelFuture cf = bootstrap.bind(61245).sync();
            //给cf注册监听器,监听我们关心的事件
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (cf.isSuccess()){
                        System.out.println("监听端口成功");
                    }else {
                        System.out.println("监听端口失败");
                    }
                }
            });
            //对通道关闭进行监听，closeFuture是异步操作,监听通道关闭
            //通过sync方法同步等待通道关闭处理完毕，这里会阻塞等待通道关闭
            cf.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) {
        new NettyServer().start();
    }
}


