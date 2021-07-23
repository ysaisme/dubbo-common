package com.ysa.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ysa
 * @date 2019/4/3 13:20
 * @description 接口端公共请求参数
 */
@Data
public class YsaCommonRequestArgs implements Serializable {

    /**
     * 用户uid
     */
    private Long uid;

    /**
     * 用户设备
     *
     */
    private String device;

    /**
     * 设备的deviceId
     */
    private String deviceId;

    /**
     * app或者web 版本
     *
     * @see com.ysa.common.util.VersionCompareUtils
     */
    private String version;

    /**
     * 请求的地域id
     */
    private Integer regionId;

    /**
     * 请求的 13位时间戳
     */
    private Long timestamp;

    /**
     * 请求的签名值
     */
    private String sign;
}
