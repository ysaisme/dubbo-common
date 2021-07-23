package com.ysa.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author hyj
 * @Date 2019/7/9 11:33 AM
 * @Description: 分页返回父类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YsaBasePageVO<T extends Serializable> implements Serializable {

    public YsaBasePageVO(List<T> resultList) {
        this.resultList = resultList;
    }

    /**
     * 列表数据
     */
    private List<T> resultList;

    /**
     * 列表数据总数
     */
    private Integer recordCount;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页条数
     */
    private Integer pageSize;

    /**
     * 列表查询时额外需要返回的参数
     */
    private Map<String, Object> extraMap;

    /**
     * @return 当前页数
     */
    public Integer getPageCount() {
        int pageCount = recordCount / pageSize;
        if (recordCount % pageSize > 0) {
            pageCount++;
        }
        if (pageCount == 0) {
            pageCount = 1;
        }
        return pageCount;
    }
}
