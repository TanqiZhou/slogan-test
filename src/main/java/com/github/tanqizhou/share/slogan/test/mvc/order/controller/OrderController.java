package com.github.tanqizhou.share.slogan.test.mvc.order.controller;

import com.github.tanqizhou.share.slogan.test.mvc.order.entity.Order;
import com.github.tanqizhou.share.slogan.test.mvc.order.service.impl.OrderServiceImpl;
import com.github.tanqizhou.share.slogan.test.mvc.order.service.mq.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/04/22/15:35
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping("/add")
    public ResponseEntity<String> addOrder(String goods_name, BigDecimal price) {
        Order order = new Order();
        order.setGoodsName(goods_name);
        order.setPrice(price);
        // boolean addOrder = orderService.addOrder(order);
        // if (addOrder) {
        //     return ResponseEntity.ok("添加成功");
        // }
        Map<String, Object> map = new HashMap<>();
        map.put(KafkaHeaders.TOPIC, Topic.SIMPLE);
        // Msg<Order> msg = new Msg<>();
        // msg.setData(order);
        Message<Order> genericMessage = new GenericMessage<>(order,new MessageHeaders(map));
        ListenableFuture<SendResult<String, Order>> send = kafkaTemplate.send(genericMessage);
        try {
            SendResult<String, Order> stringStringSendResult = send.get();
            log.info(stringStringSendResult.toString());
            return ResponseEntity.ok("添加成功");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping("/list")
    public Page<Order> listOrder() {
        return orderService.listOrder();
    }

}
