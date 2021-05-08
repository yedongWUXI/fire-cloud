package com.taurus.flow.vo.flowable;

import lombok.Data;

import java.io.Serializable;

/**
 * @title: : FormInfoQueryVo
 * @projectName : flowable
 * @description: formInfo查询参数
 */
@Data
public class FormInfoQueryVo implements Serializable {

    /**
     * 任务id
     */
    private String taskId;
    /**
     * 流程实例id
     */
    private String processInstanceId;
    /**
     * 表单id
     */
    private String businessKey;

}
