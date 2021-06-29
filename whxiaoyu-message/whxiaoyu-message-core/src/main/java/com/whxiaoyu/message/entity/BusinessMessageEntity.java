package com.whxiaoyu.message.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 业务消息实体类
 * @author jinxiaoyu
 */
@Builder
@Getter
@Setter
public class BusinessMessageEntity implements Serializable {

    private static final long serialVersionUID = 6009779993154046905L;

    /**
     * 系统类型
     */
    private String systemType;

    /**
     * topic
     */
    @NotBlank
    private String topic;

    /**
     * 消息体
     */
    @NotNull
    private String body;

    /**
     * 消息ID
     */
    private String msgId;

    /**
     * 状态
     * <p>
     * WAIT_VERIFY：待确认
     * SENDING：发送中
     */
    private String status;

    /**
     * 消息创建时间
     */
    private LocalDateTime createTime = LocalDateTime.now(ZoneId.systemDefault());
}
