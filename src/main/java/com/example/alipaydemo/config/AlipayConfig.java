package com.example.alipaydemo.config;

import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AlipayConfig extends Config {

    public Config Aliconfig() {
        Config config = new Config();
        //沙箱支付宝地址
        config.gatewayHost = "openapi-sandbox.dl.alipaydev.com";
        //协议https
        config.protocol = "https";
        //应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
        config.appId = "9021000136692252";
        //支付宝公钥
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAty5nIo47aIyAn/Ln/EkNvy4TScuRMUtD0lSPeEYUG2zyz3VIxWKooWesyDQ3jSPoYsOCQfHnE9hs7a7tvxMUzyQ0gW86IzHHIJ+q0AxMuNEqvsllOAanlxiPLr7o3pKglqMpQZhKK5/dTPTpUQJyFx3VYF6ksMDF+KUcHSh6i4HAWzilCBwHv9YKKeGGCpUzKhBUcJC0nhs7rwRWG3kGjVDaFNuFqG5XLHDKRxkE06lKfA2mpoGXbpeX3BNRcxQFaQGZB7MQ3PD+rNRblbf6pmHuKSKL0I20H1MmVBHrCX+ViEL1r/LbvrjVi1h8S17IXy42XagEmnCvFPGOSIrLnQIDAQAB";        //签名方式
        config.signType = "RSA2";
        //商户私钥（应用私钥），您的PKCS8格式RSA2私钥(支付宝开放平台)
        //config.merchantPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCL1GjHWX7tpctmSh/ohBz6CoDz5uWOiWUrOS0oqO7ywQMQafB13IoXivmEYwoJqPFjeSdm+5UvW+jZnsgOdO8P4dxyD7jTeeOMaLHSr7JPisGH2qfGcvqFv9wE8qnyvO3RSgtzaMXdh0hzHysPIJ/+wKjcbqzpEA0seSoDQstSpXJO5LZPt93aaSP4MDjoG0omUEsLyWgsdqE68a761HCzJHeJicTZfE7v/d0/AntrCcEMHygJrDXJW8kke3Bp32Yb5XxMKQzMCY1775e6equ+sILB1IRkFYBXUB8xEQaM3cr2Z5rKz2UtX+K2XV3/slSdIgjbL/2V11aoqUFNpCS1AgMBAAECggEAK1ZyAxch19YC7Hkc2pvXv0dyICX4D+1Vhcf6zxdMt8BKitPKyzzHL/TK0NVBTy0lY4cxRyHCYyNBT/F+X7yuO2XtRQzKZADrHaF1O69ovG5s/QYv6bnJG5sTPhVXxvv3WV5xnhti9/3t3oBSgCXEcNiVfXxv6uwmqRoEg244Riy7Kkmuis65kHqtc8MyOAIG1sGS4bBUA4x+SHlG+4v9/ectdAZdU/6v1SlaYt2hQP/YWGEZeN9/U3SeUyowO/UFwvJ7kpiYzHFlcbUJaVYNDYuxBTYPZaQUvqyH5CEUCoui3X+PjH5XxPIG0i75zonEFAb1op391haFb3IPdzSRIQKBgQDQA+1s7clawtbte+3fG0eirZZA3V++yeCIhZ1aYFvhFkn0VziTNFA7055F4kDj69MsowqcdWlzWsPJcBAW4HPCIuT0cmxpnwKbmxNVtT3KNYrhV3Bgxk6lgRWNbGRDgBU7xxbARMABBa3m5NOY0IY/v7X9XQ5w1E5RJmjy4kT7SwKBgQCsFd6spAIEdpj+i8dELsb26dl6KLnZINc1LgZl4oZa2zNMggX1uWNHl98kqzMSOIUBmneiivjzmTGjDCHnoCQh5/WPJZtsKV6kAC+0E4/hyvpK/tH7ipNnJ0cXDalU5qzDf/JqdCO0Xzp0wgnfBv97orPQFe5S7j9fkrE/E+df/wKBgQDDaU0brW4gwzShOHDHzJrJKgUivlP5qTAzUEH36tmlZAjpbx6IuvY8nZnr0JZhBuME5c1MGciMQ1H15D5ZH1XfpGQtd2h8+JG8mDvboj1/vjjPZBL/9otfUxpqFs9B0gsgcvDegjdVPn6Vj5Vv00SqknBmqYqtV+SSu2CP+xwObQKBgBp9z2bSZMEdJNZi3E1Byj1uqJ0msd2cMaUtLaz3n2lTP2VyysoWPRNjbOWvOVB/xPIHPo2WSVbu6XZJQtrui0Fw/RuJVZDkGX6zzKQzMr42yitLaTYJEkIaCNaissOwMz5xbvc2Y9y7oDsjN/POlvwIgxfwmFzJ8pWa9pBYVxEvAoGAJacRD+sq2AMce8ycjwMETHlvFVATQN1c48XAkb6Pjp8OIFhKQ5qTLQvu0rZfQA/Db+WkU45uD6RIXi26O+FnzmmsMkML8UKKFZfBzTlbU4hEaJACUd0tpwkGxDwRm+LoD0WQrUxlWFRXIECjtuus1uW0ULZpwzu2qfP2n+1ugZg=";
        config.merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCi+AQhYuzbMK94XYGyW+BCOCWHuxJaKR4cvG4GFSEiRiW3HdXI4t7+yBbW7wIZZQucOjpM3OOuSnJ+X4F8MDA+40u8EtVXJOv268UM0OKQfBnQhvndPEgJmndmCqDFt79vN48nK7X8NqHaTh1eyE/rfUIn2wHNs8yr4GrZgb6gRceEOEESEJn9yCFLc+82FcgvCTyicVtz13URNwRENmpqObh0+8318IA2MSrGGOEh5Ms+Fs/dBLeyFn7ZIrszqjpAEbanvm/hob0E3zvdUTV8HompkvGDf79KfDTs1vlW+FR+hFnb+YK2AAvE4gjLzUM3Tp7c1tMLspqOkyRyxMbrAgMBAAECggEAekRbTPUcQQLQ0xxMXxVhNi1Lsv6eMTiiF+sjHLsjhcRdLXpb14Cmzz4tLIpa59SQlmB1Nw+OXkgKiFd9HUBGIhiDzJHQr7XKtxGCYDAjm0UQVQu/UMWWQmY6aDtXDZ/jJzzm0PuRaK1QVym+DUt58nKifKZqPvtDdjxDzfXyn+kjJ7hc8h0+xu8wfaO5lax5Hp/33F1Ss27Q45pSbOCwK9IeanYWQcQZgV01WP+66fCBl6r8PfIIoCKEW8eSkA3nt2XxOj086XuvRiQnBfL8r4SjauRwc8rednvy4Scn3D3KajrIH77p7sQAV0HBscvvrSE7HCcPkUzGFhsdxVc7EQKBgQDzX0cqsenNiIPvsZwyEIRf6ZtdlLBtpuHVWgPJtQ4fErkdOSO2FfSuz4/rw8QjPSzvB8s5z3XzfEvLhgEls0sdcPBzOszYy29al3oa8zj2JDvTXXiv4c9eI/axos10CdtPe/fm6EPneX/aiAVtfOv6lO29vjuTkneZdaeyS7neyQKBgQCrbLy0ytJuvVMxCh7X4qtA7MhFEZxXnpUKgS+61N34higWwXeXdjdfxBKwBAx8aEpRM7Rg5QX85Ixe2jcMelWwnaNvENZ1SY+1J6K8ktf1hb+NfwHwqIytTfl4iYUvqtUv1BBNeP3BRuSxh9MsNVAKMnw+YIEvfNTln4NCNVhOEwKBgBn52mu5L55LauGjkGtbjfV6xqV3YFljzYjrIape09IHOpDe72vY7hPWfaMJcx7bwbcFmvBhr1I8uw5PEEJI38SVsGRsKz8cf5cowdSsjzDebzBBMsd7D5oZtpQBL3KqNqlGLxCLS+wh8L6PdBzaj4lgafutmUhIAtHzV+HU6VLZAoGAWXrz8achOr7GiwWOXgAqhkRSsYLkyfO0NnyCjpKIny5gBKjOJRiHBQ0S+B/U6KeDi2li0WUawAwInYZoT7IXwo42fkYau7Q/OD9NOgOoiLdc6p4ORDGm5Nubyeh2xg/1Ifce32G1i0rXGzgb6aqWcgwyQFkddc1CT+34lPnFc1MCgYAlI48uWxtPvxmQLrgBIYuI5Y2YC4SN8jPfBYxmtCA1DUSPZqNantA6FsajNFIr+7VUgS9LdmNm5TLT1ZYaHoBlohRatcj4DLuxSG7qNd6sHWixmbAOzW0pO0htUK/rp5aPxbs5l0a23GncgpQabDezvnEAjkH7o3tF/HEVtl6+QQ==";
        config.notifyUrl = "https://3a63d596.r9.cpolar.top/notify";
        return config;
    }
}

//    public String goAlipay(Order order) {
//        try {
//            // 1. 设置参数（全局只需设置一次）
//            Factory.setOptions(aliconfig());
//            // 2. 发起API调用（subject商品标题、outTradeNo订单编号、totalAmount总金额、returnUrl异步通知地址）
//            AlipayTradePagePayResponse response = Factory.Payment.Page()
//                    .pay("商城项目收款",
//                            order.getOid().toString(),
//                            order.getTotal().toString(),
//                            //支付成功之后的异步通知（跳出到自己系统的哪个位置）
//                            "http://localhost:8080/order/payDone");
//            return response.body;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//}
