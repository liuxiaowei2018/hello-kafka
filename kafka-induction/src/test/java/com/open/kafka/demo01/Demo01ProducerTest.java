package com.open.kafka.demo01;

import com.open.kafka.HelloKafkaApplication;
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
public class Demo01ProducerTest {

    @Autowired
    private Demo01Producer producer;

    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testASyncSend() throws InterruptedException {
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

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}