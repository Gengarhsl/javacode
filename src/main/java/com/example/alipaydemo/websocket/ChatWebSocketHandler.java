package com.example.alipaydemo.websocket;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.alipaydemo.controller.pojo.Message;
import com.example.alipaydemo.entity.ChatGroupUser;
import com.example.alipaydemo.service.ChatGroupService;
import com.example.alipaydemo.service.ChatGroupUserService;
import com.example.alipaydemo.service.MassageService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Slf4j
public class ChatWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Map<String, Map<String,Channel>> userSessions = new ConcurrentHashMap<>();
    private static final Map<String, Integer> unreadMessages = new ConcurrentHashMap<>();

    private static final Map<String, Queue<String>> offlineMessages = new ConcurrentHashMap<>();

    @Resource
    private MassageService messageService;

    @Resource
    private ChatGroupService chatGroupService;

    @Resource
    private ChatGroupUserService chatGroupUserService;

    /**
     * 处理文本消息
     * @param ctx
     * @param msg
     * @throws Exception
     */

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Message data = parseMessage(msg.text());
        data.setSenderId(getUserIdFromSession(ctx));


//        if("1".equals(data.getChatType())) {
//            switch (data.getContentType()) {
//                case "text":
//                    broadcastTextMessage(data.getContent(), ctx.channel());
//                    break;
//                case "image":
//                    broadcastImageMessage(data.getContent(), ctx.channel());
//                    break;
//                case "audio":
//                    broadcastAudioMessage(data.getContent(), ctx.channel());
//                    break;
//                case "video":
//                    broadcastVideoMessage(data.getContent(), ctx.channel());
//                    break;
//                case "file":
//                    broadcastFileMessage(data.getContent(), ctx.channel());
//                    break;
//                case "link":
//                    broadcastLinkMessage(data.getContent(), ctx.channel());
//                    break;
//                default:
//                    // 默认处理
//                    broadcastTextMessage(payload, ctx.channel());
//                    break;
//            }
//        }else if("2".equals(data.getChatType())) {
//
//            switch (data.getContentType()) {
//                case "text":
//                    broadcastMessageToUser(data.getReceiverId(), data.getContent(), ctx.channel());
//                    break;
//                case "image":
//                    broadcastMessageToUser(data.getReceiverId(), data.getContent(), ctx.channel());
//                    break;
//                case "audio":
//                    broadcastMessageToUser(data.getReceiverId(), data.getContent(), ctx.channel());
//                    break;
//                case "video":
//                    broadcastMessageToUser(data.getReceiverId(), data.getContent(), ctx.channel());
//                    break;
//                case "file":
//                    broadcastMessageToUser(data.getReceiverId(), data.getContent(), ctx.channel());
//                    break;
//                case "link":
//                    broadcastMessageToUser(data.getReceiverId(), data.getContent(), ctx.channel());
//                    break;
//                default:
//                    // 默认处理
//                    broadcastMessageToUser(data.getReceiverId(), data.getContent(), ctx.channel());
//                    break;
//            }
//
//        }
        // 私聊
        if("2".equals(data.getChatType())) {
            // 广播消息到指定用户的所有连接端
            broadcastMessageToUser(data.getReceiverId(), data.getContent(), ctx.channel());
            // 保存聊天内容
            messageService.save(data);
            log.info("转发消息：[{}]", data.getContent());
        }

        // 群聊
        if("1".equals(data.getChatType())) {
            // 广播消息到群聊用户的所有连接端
            broadcastTextMessage(data.getGroupId(), data.getContent(), ctx.channel());
            // 保存聊天内容
            messageService.save(data);
            log.info("转发消息：[{}]", data.getContent());
        }
    }

    /**
     * 处理连接事件
     * @param ctx
     * @throws Exception
     */

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
        storeOfflineMessages(ctx, userId);
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

//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent event = (IdleStateEvent) evt;
//            if (event.state() == IdleStateEvent.State.ALL_IDLE) {
//                // 心跳超时处理
//                ctx.close();
//            }
//        } else {
//            super.userEventTriggered(ctx, evt);
//        }
//    }
//
    private void broadcastTextMessage(String groupId,String content, Channel sender) {
        // 获取查询结果的 Optional
        Optional<List<ChatGroupUser>> optionalList = Optional.ofNullable(
                chatGroupUserService.lambdaQuery().eq(ChatGroupUser::getGroupId, groupId).list()
        );

// 从 Optional 中提取 userIds，处理 list 可能为 null 的情况
        List<String> userIds = optionalList
                // 如果 list 为 null，则使用空列表
                .orElse(Collections.emptyList())
                .stream()
                .map(ChatGroupUser::getUserId)
                .collect(Collectors.toList());
        Map<String, Channel> stringChannelMap = userSessions.get(userIds);
        List<Channel> channelList = Optional.ofNullable(stringChannelMap)
                .map(map -> map.values().stream()
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        channelList.stream().filter(channel -> !channel.equals(sender))
                .forEach(channel -> channel.writeAndFlush(new TextWebSocketFrame(content)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE));
        for (Map.Entry<String, Channel> entry : userSessions.entrySet()) {
            Channel recipient = entry.getValue();
            if (!recipient.equals(sender)) {
                recipient.writeAndFlush(new TextWebSocketFrame(content)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                incrementUnreadMessages(getUserIdFromSession(recipient));
                storeOfflineMessageIfNecessary(recipient, content);
            }
        }
    }
//
//    private void broadcastImageMessage(String content, Channel sender) {
//        for (Map.Entry<String, Channel> entry : sessions.entrySet()) {
//            Channel recipient = entry.getValue();
//            if (!recipient.equals(sender)) {
//                recipient.writeAndFlush(new TextWebSocketFrame(content)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//                incrementUnreadMessages(getUserIdFromSession(recipient));
//                storeOfflineMessageIfNecessary(recipient, content);
//            }
//        }
//    }
//
//    private void broadcastAudioMessage(String content, Channel sender) {
//        for (Map.Entry<String, Channel> entry : sessions.entrySet()) {
//            Channel recipient = entry.getValue();
//            if (!recipient.equals(sender)) {
//                recipient.writeAndFlush(new TextWebSocketFrame(content)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//                incrementUnreadMessages(getUserIdFromSession(recipient));
//                storeOfflineMessageIfNecessary(recipient, content);
//            }
//        }
//    }
//
//    private void broadcastVideoMessage(String content, Channel sender) {
//        for (Map.Entry<String, Channel> entry : sessions.entrySet()) {
//            Channel recipient = entry.getValue();
//            if (!recipient.equals(sender)) {
//                recipient.writeAndFlush(new TextWebSocketFrame(content)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//                incrementUnreadMessages(getUserIdFromSession(recipient));
//                storeOfflineMessageIfNecessary(recipient, content);
//            }
//        }
//    }
//
//    private void broadcastFileMessage(String content, Channel sender) {
//        for (Map.Entry<String, Channel> entry : sessions.entrySet()) {
//            Channel recipient = entry.getValue();
//            if (!recipient.equals(sender)) {
//                recipient.writeAndFlush(new TextWebSocketFrame(content)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//                incrementUnreadMessages(getUserIdFromSession(recipient));
//                storeOfflineMessageIfNecessary(recipient, content);
//            }
//        }
//    }
//
//    private void broadcastLinkMessage(String content, Channel sender) {
//        for (Map.Entry<String, Channel> entry : sessions.entrySet()) {
//            Channel recipient = entry.getValue();
//            if (!recipient.equals(sender)) {
//                recipient.writeAndFlush(new TextWebSocketFrame(content)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//                incrementUnreadMessages(getUserIdFromSession(recipient));
//                storeOfflineMessageIfNecessary(recipient, content);
//            }
//        }
//    }

//    private void sendMessageToUser(String receiverId, String content, Channel sender) {
//        String sessionId = sender.id().asShortText();
//        Channel recipient = userSessions.get(receiverId).get(sessionId);
//        if (recipient != null && !recipient.equals(sender)) {
//            recipient.writeAndFlush(new TextWebSocketFrame(content)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//            incrementUnreadMessages(receiverId);
//            storeOfflineMessageIfNecessary(recipient, content);
//        } else {
//            storeOfflineMessage(receiverId, content);
//        }
//    }

    private void broadcastMessageToUser(String receiverId, String content, Channel sender) {
        Map<String, Channel> sessions = userSessions.get(receiverId);
        if (sessions != null) {
            for (Map.Entry<String, Channel> entry : sessions.entrySet()) {
                Channel recipient = entry.getValue();
                if (recipient != null && !recipient.equals(sender)) {
                    recipient.writeAndFlush(new TextWebSocketFrame(content)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                    incrementUnreadMessages(receiverId);
                }
            }
        } else {
            storeOfflineMessage(receiverId, content);
        }
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


    private Message parseMessage(String payload) {
        // 简单解析payload
//        String[] parts = payload.split(",");
//        Message message = JSONUtil.toBean(payload, Message.class);
        Message message = new Message();
        message.setReceiverId(payload);
        message.setContent(payload);
        return message;
    }

    private void sendHistoryMessages(ChannelHandlerContext ctx, String userId, String sessionId) {
        // 发送历史消息
        try {
            ctx.channel().writeAndFlush(new TextWebSocketFrame("History message 1")).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            ctx.channel().writeAndFlush(new TextWebSocketFrame("History message 2")).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendOfflineMessages(ChannelHandlerContext ctx, String receiverId, String sessionId) {
        Queue<Message> messages = offlineMessages.get(receiverId);
        if (messages != null && !messages.isEmpty()) {
            while (!messages.isEmpty()) {
                Message message = messages.poll();
                if (message.getSessionId().equals(sessionId)) {
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(message.getContent())).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                }
            }
        }
    }

    /**
     * 增加未读消息数量
     * @param receiverId
     */
    private void incrementUnreadMessages(String receiverId) {
        unreadMessages.computeIfAbsent(receiverId, k -> 0);
        unreadMessages.put(receiverId, unreadMessages.get(receiverId) + 1);
    }

    /**
     * 存储离线消息，如果用户不在线，则存储在离线消息队列中
     * @param recipient
     * @param content
     */
    private void storeOfflineMessageIfNecessary(Channel recipient, String content) {
        String userId = getUserIdFromSession(recipient);
        if (!userSessions.containsKey(userId)) {
            storeOfflineMessage(userId, content);
        }
    }

    /**
     * 存储离线消息，如果用户不在线，则存储在离线消息队列中
     * @param userId
     * @param ctx
     */

    private void storeOfflineMessages(ChannelHandlerContext ctx, String userId) {
        String sessionId = getSessionIdFromContext(ctx);
        String channelId = ctx.channel().id().asShortText();
        if (unreadMessages.getOrDefault(channelId, 0) > 0) {
            storeOfflineMessage(channelId, "离线消息", sessionId);
        }
    }

    private void storeOfflineMessage(String receiverId, String content) {
        Queue<String> messages = offlineMessages.computeIfAbsent(receiverId, k -> new LinkedBlockingQueue<>());
        messages.offer(receiverId + content);
    }


}
