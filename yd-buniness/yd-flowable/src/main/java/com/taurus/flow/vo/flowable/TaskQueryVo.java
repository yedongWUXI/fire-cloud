package com.taurus.flow.vo.flowable;

import lombok.Data;

import java.io.Serializable;

/**
 * @title: : TaskVo
 * @projectName : flowable
 * @description: 任务VO
 */
@Data
public class TaskQueryVo implements Serializable {

    /**
     * 用户工号
     */
    private String userCode;

}
