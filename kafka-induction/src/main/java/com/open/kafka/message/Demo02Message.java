package com.open.kafka.message;

import lombok.Data;

/**
 * @author liuxiaowei
 * @date 2022年09月14日 13:27
 * @Description
 */
@Data
public class Demo02Message {

    public static final String TOPIC = "DEMO_02";

    /**
     * 编号
     */
    private Integer id;
}
