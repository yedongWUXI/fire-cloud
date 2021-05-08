package com.taurus.flow.vo.flowable;


import lombok.Data;

/**
 * @title: : DelegateTaskVo
 * @projectName : flowable
 * @description: 委派
 */
@Data
public class DelegateTaskVo extends BaseProcessVo {
    /**
     * 委派人
     */
    private String delegateUserCode;

}
