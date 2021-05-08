package com.taurus.flow.vo.flowable;

import lombok.Data;

import java.io.Serializable;

/**
 * @title: : QueryProcessInstanceVo
 * @projectName : flowable
 * @description: 查询流程实例VO
 */
@Data
public class ProcessInstanceQueryVo implements Serializable {

    private String formName;
    private String userCode;
    private String userName;

}
