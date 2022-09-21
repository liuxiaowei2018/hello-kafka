package com.open.kafka.demo02;

import com.open.kafka.HelloKafkaApplication;
import com.open.kafka.demo01.Demo01Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author liuxiaowei
 * @date 2022年09月14日 13:30
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloKafkaApplication.class)
public class Demo02ProducerTest {

    @Autowired
    private Demo02Producer producer;

    @Test
    public void testASyncSend() throws InterruptedException {
        log.info("[testASyncSend][开始执行]");

        for (int i = 0; i < 3; i++) {
            int id = (int) (System.currentTimeMillis() / 1000);
            producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
                @Override
                public void onFailure(Throwable e) {
                    log.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
                }
                @Override
                public void onSuccess(SendResult<Object, Object> result) {
                    log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
                }

            });

            // 故意每条消息之间，隔离 10 秒 = 恰好满足我们配置的 linger.ms 最大等待时长
            //Thread.sleep(10 * 1000L);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}