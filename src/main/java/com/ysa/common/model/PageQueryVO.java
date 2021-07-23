package com.ysa.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author hyj
 * @Date 2019/7/9 11:42 AM
 * @Description: 分页查询父类
 */
@Data
public class PageQueryVO extends AdminInfoVO implements Serializable {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页行数
     */
    private Integer pageSize;

    /**
     * 排序语句
     */
    private String sortCondition;

    /**
     * 分页语句组装
     */
    public String getLimit() {
        if (pageNum == null || pageSize == null) {
            return null;
        }
        return pageSize * (pageNum - 1) + "," + this.pageSize;
    }
}
