package com.example.alipaydemo.websocket;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.springframework.stereotype.Component;


@Component
public class SslContextFactory {

    public static SslContext createSslContext() throws Exception {
        // 创建自签名证书（用于测试环境）
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        return SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey())
                .sslProvider(SslProvider.JDK)
                .build();

        // 如果你有自己的证书文件，可以用以下代码
        // return SslContextBuilder.forServer(new File("path/to/cert.pem"), new File("path/to/key.pem"))
        //         .sslProvider(SslProvider.JDK)
        //         .build();
    }
}