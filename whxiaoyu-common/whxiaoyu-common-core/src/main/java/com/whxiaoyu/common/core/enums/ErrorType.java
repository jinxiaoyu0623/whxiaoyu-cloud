package com.whxiaoyu.common.core.enums;

/**
 * 程序错误类型
 * @author jinxiaoyu
 */
public interface ErrorType {

    /**
     * 返回错误code
     * @return 错误码
     */
    String getCode();

    /**
     * 返回错误信息
     * @return 错误信息
     */
    String getMsg();
}
