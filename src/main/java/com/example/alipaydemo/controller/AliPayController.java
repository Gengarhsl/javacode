package com.example.alipaydemo.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSONObject;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;

import com.example.alipaydemo.config.AlipayConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
@Log4j2
public class AliPayController {


    @Autowired
    private AlipayConfig config;

    @Autowired
    private RedisTemplate redisTemplate;



    @GetMapping("/pay")
    public String pay() throws Exception {
        Config aliconfig = config.Aliconfig();
        Factory.setOptions(aliconfig);

        AlipayTradePrecreateResponse iphone15 = Factory.Payment.FaceToFace()
                .preCreate("Apple iPhone11 128G", "22345671890", "5799.00");
        String httpBody = iphone15.httpBody;
        JSONObject jsonObject = JSONObject.parseObject(httpBody);
        String qrUrl = jsonObject.getJSONObject("alipay_trade_precreate_response").get("qr_code").toString();
        QrCodeUtil.generate(qrUrl,300,300,new File("D://pay.jpg"));
        return httpBody;
    }

    @GetMapping("notify")
    public String notify(HttpServletRequest request){
        log.info("收到支付成功通知");
        String out_trade_no = request.getParameter("out_trade_no");
        log.info("流水号：{}",out_trade_no);
        return "success";
    }

    @GetMapping("123")
    public String haha(){
        return "haha";
    }

    @GetMapping("query")
    public String query() throws Exception {
        Factory.setOptions(config.Aliconfig());
        AlipayTradeQueryResponse query = Factory.Payment.Common().query("22345671890");
        return query.httpBody;
    }


    @GetMapping("redis")
    public String redis(HttpServletRequest request, HttpServletResponse response){
        String remoteAddr = request.getRemoteAddr();
        redisTemplate.opsForValue().set(remoteAddr+"hslauth","test");
        log.info("redis连接成功");
        return "redis连接成功";
    }
}
