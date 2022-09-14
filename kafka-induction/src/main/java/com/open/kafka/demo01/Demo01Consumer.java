package com.open.kafka.demo01;

import com.open.kafka.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月14日 13:29
 * @Description
 */
@Slf4j
@Component
public class Demo01Consumer {

    @KafkaListener(topics = Demo01Message.TOPIC,
            groupId = "demo01-consumer-group-" + Demo01Message.TOPIC)
    public void onMessage(Demo01Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
