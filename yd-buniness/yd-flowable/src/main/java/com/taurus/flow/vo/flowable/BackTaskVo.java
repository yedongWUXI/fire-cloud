package com.taurus.flow.vo.flowable;

import lombok.Data;

/**
 * @Description: 驳回的实体VO
 */
@Data
public class BackTaskVo extends BaseProcessVo {

    /**
     * 需要驳回的节点id 必填
     */
    private String distFlowElementId;

}
