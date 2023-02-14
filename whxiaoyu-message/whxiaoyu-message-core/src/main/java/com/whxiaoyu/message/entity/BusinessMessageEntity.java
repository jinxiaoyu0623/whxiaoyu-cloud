package com.whxiaoyu.message.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author jinxiaoyu
 */
@Data
@Builder
public class BusinessMessageEntity {

    private String messageId;

    private String topic;

    private Object body;
}
