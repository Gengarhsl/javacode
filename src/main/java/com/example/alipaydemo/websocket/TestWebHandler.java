package com.example.alipaydemo.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-09-14 16:14
 */

@Slf4j

public class TestWebHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    private static final Map<String, Map<String, Channel>> userSessions = new ConcurrentHashMap<>();
    private static final Map<String, Integer> unreadMessages = new ConcurrentHashMap<>();

    private static final Map<String, Queue<String>> offlineMessages = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        try {
            String userId = getUserIdFromSession(ctx);

            String sessionId = getSessionIdFromContext(ctx);
            Map<String, Channel> sessions = userSessions.computeIfAbsent(userId, k -> new HashMap<>());
            List<Channel> collect = userSessions.values().stream().flatMap(map -> map.values().stream()).collect(Collectors.toList());
            collect.stream().map(channel -> channel.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + ctx.channel().remoteAddress() + " 加入")));
            sessions.put(sessionId, ctx.channel());
            log.info("userMessages: [{}]", userSessions);
            log.info("收到新的连接：[{}]", userId + "+" + sessionId);
        }catch (Exception e){
            log.error("handlerAdded异常：[{}]", e.getMessage());
        }
    }

    /**
     * 处理断开连接事件
     * @param ctx
     * @throws Exception
     */

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        try {


            String userId = getUserIdFromSession(ctx);
            String sessionId = getSessionIdFromContext(ctx);
            Map<String, Channel> sessions = userSessions.get(userId);
            if (sessions != null) {
                sessions.remove(sessionId);
            }
//        // 将未读消息存储为离线消息
//        storeOfflineMessages(ctx, userId);
            log.info("连接已断开：[{}]", userId + "+" + sessionId);

        }catch (Exception e){
            log.error("handlerRemoved异常：[{}]", e.getMessage());
        }
    }


    /**
     * 处理连接事件 在线
     * @param ctx
     * @throws Exception
     */

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        try {


            String userId = getUserIdFromSession(ctx);
            String sessionId = getSessionIdFromContext(ctx);
            // 发送历史消息
//            sendHistoryMessages(ctx, userId, sessionId);
            // 发送离线消息
//        sendOfflineMessages(ctx, userId, sessionId);
            log.info("连接在线：[{}]", userId + "+" + sessionId);
        }catch (Exception e){
            log.error("channelActive异常：[{}]", e.getMessage());
        }
    }

    /**
     * 处理断开连接事件 离线
     * @param ctx
     * @throws Exception
     */

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

        try {

            String userId = getUserIdFromSession(ctx);
            String sessionId = getSessionIdFromContext(ctx);
            // 清除未读消息计数
            unreadMessages.put(userId, 0);
            log.info("连接离线：[{}]", userId + "+" + sessionId);
        }catch (Exception e){
            log.error("channelInactive异常：[{}]", e.getMessage());
        }
    }

    /**
     * 处理连接事件  异常
     * @param ctx
     * @throws Exception
     */

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "异常");
// 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }


    private String getUserIdFromSession(ChannelHandlerContext ctx) {
        // 根据实际情况获取用户ID
        String userId = "123456";
        // 示例中使用channel ID作为用户ID
//        return ctx.channel().id().asShortText();
//        String sessionsId = userId + "_" + ctx.channel().id().asShortText();
        return userId;
    }

    private String getSessionIdFromContext(ChannelHandlerContext ctx) {
        // 假设从上下文中获取会话ID
        return ctx.channel().id().asShortText(); // 示例中使用channel ID作为会话ID
    }
}
