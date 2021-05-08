package com.taurus.flow.vo.flowable;

import lombok.Data;

import java.io.Serializable;

/**
 * @title: : ProcessDefinitionQueryVo
 * @projectName : flowable
 * @description: 流程定义查询Vo
 */
@Data
public class ProcessDefinitionQueryVo implements Serializable {

    private String name;
    private String modelKey;

}
