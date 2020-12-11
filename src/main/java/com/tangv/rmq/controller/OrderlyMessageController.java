package com.tangv.rmq.controller;

import com.tangv.rmq.service.OrderlyMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:   tangwei
 * Date:     2020/12/11 17:41
 * Description: 顺序消息生产
 */

@RestController
@RequestMapping("/msg/")
public class OrderlyMessageController {

    @Autowired
    private OrderlyMsgService orderlyMsgService;

    @PostMapping("send/orderly")
    public void sendOrderlyMsg() {
        orderlyMsgService.sendOrderlyMsg();;
    }

}