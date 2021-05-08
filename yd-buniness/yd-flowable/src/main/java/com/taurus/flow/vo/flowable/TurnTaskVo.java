package com.taurus.flow.vo.flowable;


import lombok.Data;

/**
 * @title: : TurnTaskVo
 * @projectName : flowable
 * @description: 转办Vo
 */
@Data
public class TurnTaskVo extends BaseProcessVo {

    /**
     * 被转办人工号 必填
     */
    private String turnToUserId;


}
