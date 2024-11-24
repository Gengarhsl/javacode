package com.example.alipaydemo.controller;

import com.example.alipaydemo.config.WebSocketServer;
import com.example.alipaydemo.demo.AjaxJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanjinqun
 * @date 2022/10/24
 * websocket接口
 */
@RestController
@RequestMapping(value = "/api/v1/websocket")
@Api(tags = "websocket接口", value = "AlarmDpController")
public class WebSocketController {
    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 模拟数据发送
     */
    @ApiOperation(value = "模拟数据发送", notes = "模拟数据发送")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "message", value = "模拟消息", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/sendTestMessage", method = RequestMethod.GET)
    public AjaxJson sendTestMessage(String message) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            webSocketServer.sendAllMessage(message);
            ajaxJson.setMsg("Message sent successfully");
        } catch (Exception e) {
            ajaxJson = AjaxJson.returnExceptionInfo("Failed to send message: " + e.getMessage());
        }
        return ajaxJson;
    }

}
