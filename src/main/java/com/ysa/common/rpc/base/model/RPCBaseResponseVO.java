package com.ysa.common.rpc.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2021/7/11 17:41
 * Modified By:
 */
@Data
public class RPCBaseResponseVO<T> implements Serializable {

    private Boolean isSuccess;

    private String code;

    private String msg;

    private T data;

    public RPCBaseResponseVO() {
        this.isSuccess = Boolean.TRUE;
        this.code = null;
        this.msg = null;
        this.data = null;
    }

    public RPCBaseResponseVO(String code, String msg) {
        this.isSuccess = Boolean.TRUE;
        this.code = code;
        this.data = null;
    }

    public RPCBaseResponseVO(T data) {
        this.isSuccess = Boolean.TRUE;
        this.data = data;
    }

    public RPCBaseResponseVO(String code, T data) {
        this.isSuccess = Boolean.TRUE;
        this.code = code;
        this.data = data;
    }

    public RPCBaseResponseVO(String code, String msg, T data) {
        this.isSuccess = Boolean.TRUE;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
