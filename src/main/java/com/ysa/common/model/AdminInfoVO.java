package com.ysa.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author hyj
 * @Date 2019/7/9 11:41 AM
 * @Description: 管理员动作对象
 */
@Data
public class AdminInfoVO implements Serializable {

    /**
     * 操作人Id
     */
    private Integer adminId;

    /**
     * 操作人名字
     */
    private String adminName;

    /**
     * 管理员操作的缘由,没有就不传
     */
    private String operationReason;

    /**
     * 管理操作额外的data
     */
    private Map<String, String> adminExtraMap;
}
