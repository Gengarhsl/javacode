//package com.example.alipaydemo.controller;
//
//import cn.hutool.extra.qrcode.QrCodeUtil;
//import com.example.alipaydemo.controller.pojo.User;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@Slf4j
//public class TestController {
//
//    @GetMapping("test")
//    public ResponseEntity<byte[]> test() {
//        User user = new User();
//        user.setId(1);
////        user.setName("侯释龙");
////        user.setNumber("test001");
////        user.setBodyTemperature("38.5");
////        user.setBloodPressure("500");
////        user.setWeight("55");
////        user.setCode(1);
////        user.setIcode(1);
////        user.setSpecial("老人");
//        String data = user.toString();
//        BufferedImage qrCode = QrCodeUtil.generate(data, 300, 300);
//        byte[] qrCodeBytes = convertImageToByteArray(qrCode);
//
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCodeBytes);
//    }
//
//    private byte[] convertImageToByteArray(BufferedImage image) {
//        try {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            ImageIO.write(image, "png", byteArrayOutputStream);
//            return byteArrayOutputStream.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
