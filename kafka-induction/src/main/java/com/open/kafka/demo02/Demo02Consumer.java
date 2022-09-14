package com.open.kafka.demo02;

import com.open.kafka.message.Demo02Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuxiaowei
 * @date 2022年09月14日 13:29
 * @Description
 */
@Slf4j
@Component
public class Demo02Consumer {

//    @KafkaListener(topics = Demo02Message.TOPIC,
//            groupId = "demo02-consumer-group-" + Demo02Message.TOPIC)
//    public void onMessage(Demo02Message message) {
//        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
//    }

    @KafkaListener(topics = Demo02Message.TOPIC,
            groupId = "demo02-consumer-group-" + Demo02Message.TOPIC)
    public void onMessage(List<Demo02Message> message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
