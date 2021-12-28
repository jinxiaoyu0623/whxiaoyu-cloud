package com.whxiaoyu.component.job.properties;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author jinxiaoyu
 */
@Getter
@Setter
public class XxlJobProperties implements Serializable {

    private static final long serialVersionUID = -4844425271871426517L;


    /**
     * 调度中心地址 such as "http://address" or "http://address01,http://address02"
     */
    private String addressAddr;



}
