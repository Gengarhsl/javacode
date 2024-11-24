package com.example.alipaydemo.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class WebSocketFrameHandler extends ChannelInboundHandlerAdapter {

    private static final String HEARTBEAT_MESSAGE = "heartbeat";
    private static final long HEARTBEAT_INTERVAL_MS = 30000; // 30 seconds

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                System.out.println("Read idle detected");
                // Consider closing the connection if no data was read
                ctx.close();
            } else if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                System.out.println("Write idle detected");
                // Send heartbeat frame to the client
                ctx.writeAndFlush(new TextWebSocketFrame(HEARTBEAT_MESSAGE));
            } else if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                System.out.println("All idle detected");
                // Handle all idle state
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof TextWebSocketFrame) {
            String message = ((TextWebSocketFrame) msg).text();
            if (HEARTBEAT_MESSAGE.equals(message)) {
                System.out.println("Received heartbeat from client");
                // Reset idle state counter if needed
            } else {
                // Handle other messages
                System.out.println("Received message: " + message);
            }
        } else {
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}