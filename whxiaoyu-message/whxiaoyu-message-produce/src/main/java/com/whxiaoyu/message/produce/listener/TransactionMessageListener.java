package com.whxiaoyu.message.produce.listener;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

/**
 * @author jinxiaoyu
 */
@RocketMQTransactionListener
public class TransactionMessageListener implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        return null;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String tranceId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        if (null != tranceId) {
            switch (tranceId) {
                case "1":
                    return RocketMQLocalTransactionState.COMMIT;
                case "2":
                    return RocketMQLocalTransactionState.ROLLBACK;
                default:
                    return RocketMQLocalTransactionState.UNKNOWN;
            }
        }
        return RocketMQLocalTransactionState.COMMIT;
    }
}
