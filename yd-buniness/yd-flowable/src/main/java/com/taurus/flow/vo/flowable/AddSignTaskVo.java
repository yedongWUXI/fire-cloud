package com.taurus.flow.vo.flowable;

import lombok.Data;

import java.util.List;

/**
 * @title: : AddSignTaskVo
 * @projectName : flowable
 * @description: 加签Vo
 */
@Data
public class AddSignTaskVo extends BaseProcessVo {
    /**
     * 被加签人
     */
    private List<String> signPersoneds;

}
