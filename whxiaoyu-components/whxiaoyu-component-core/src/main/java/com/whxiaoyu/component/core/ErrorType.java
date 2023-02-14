package com.whxiaoyu.component.core;

/**
 * 错误类型
 *
 * @author jinxiaoyu
 */
public interface ErrorType {

    /**
     * 返回错误code
     * @return 错误码
     */
    int getCode();

    /**
     * 返回错误信息
     * @return 错误信息
     */
    String getMsg();
}
