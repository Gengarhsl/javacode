//package com.example.alipaydemo.impl;
//
//import com.example.alipaydemo.service.NettyServer;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.*;
//import org.springframework.web.socket.client.WebSocketClient;
//import org.springframework.web.socket.client.standard.StandardWebSocketClient;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//import org.springframework.util.concurrent.ListenableFuture;
//
//import javax.annotation.Resource;
//import java.net.URI;
//import java.io.IOException;
//import java.util.concurrent.ExecutionException;
//
//@Configuration
//public class WebSocketClientConfig {
//
//    @Resource
//    private NettyServer nettyServer;
//
//    @Bean
//    public CommandLineRunner runWebSocketClient() {
//
//        nettyServer.start();
//        return args -> {
//            WebSocketClient client = new StandardWebSocketClient();
//            WebSocketHandler handler = new TextWebSocketHandler() {
//                @Override
//                public void afterConnectionEstablished(WebSocketSession session) {
//                    System.out.println("Connected to server");
//                    try {
//                        session.sendMessage(new TextMessage("Hello, Server!"));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//                public void handleMessage(WebSocketSession session, TextMessage message) {
//                    System.out.println("Received: " + message.getPayload());
//                }
//
//                @Override
//                public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//                    System.out.println("Connection closed");
//                }
//            };
//
//            ListenableFuture<WebSocketSession> future = client.doHandshake(handler, new WebSocketHttpHeaders(), new URI("ws://localhost:61245/ws"));
//
//            try {
//                WebSocketSession session = future.get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        };
//    }
//}