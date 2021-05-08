package com.taurus.flow.vo.flowable;

import lombok.Data;

import java.io.Serializable;

/**
 * @title: : ModelVo
 * @projectName : flowable
 * @description: modelVo
 */
@Data
public class ModelVo implements Serializable {
    //流程id
    private String processId;
    //流程名称
    private String processName;
    /**
     * 分类Id
     */
    private String categoryId;
    //流程的xml
    private String xml;

}
