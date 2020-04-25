package com.github.tanqizhou.share.slogan.test.mvc.order.service.mq;

import com.github.tanqizhou.share.slogan.test.mvc.order.entity.Order;
import com.github.tanqizhou.share.slogan.test.mvc.order.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/04/22/16:55
 * @Description:
 */
@Slf4j
@Service
public class OrderMqService {

    @Autowired
    OrderServiceImpl orderService;

    @KafkaListener(groupId = "order", topics = Topic.SIMPLE)
    public void onMessage(Order order, Acknowledgment ack) {
        boolean b = orderService.addOrder(order);
        if (!b) {
            log.error("添加订单失败，管理员处理");
            throw new KafkaException("添加订单失败，管理员处理");
        }
        ack.acknowledge();
        log.info("消息消费成功");
    }

    // @SendTo注解还可以带一个参数，指定转发的Topic队列。
    // @KafkaListener(id="webGroup",topics="topic-kl")
    // @SendTo("topic-ckl")
    public String listen(@Payload String input, @Headers Map headers, @Header(name = "name") Object name) {
        log.info("inputvalue:{}",input);
        return input+"hello!";
    }


}
