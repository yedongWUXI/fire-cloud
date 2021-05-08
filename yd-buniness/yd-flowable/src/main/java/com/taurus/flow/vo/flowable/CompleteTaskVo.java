package com.taurus.flow.vo.flowable;


import lombok.Data;

import java.util.Map;

/**
 * @title: : CompleteTaskVo
 * @projectName : flowable
 * @description: 执行任务Vo
 */
@Data
public class CompleteTaskVo extends BaseProcessVo {
    /**
     * 任务参数 选填
     */
    private Map<String, Object> variables;

}
