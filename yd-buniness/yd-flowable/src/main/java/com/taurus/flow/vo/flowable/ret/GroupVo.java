package com.taurus.flow.vo.flowable.ret;

import java.io.Serializable;

/**
 * @title: : GroupVo
 * @projectName : flowable
 * @description: 组的VO
 */
public class GroupVo implements Serializable {

    /**
     * 组的id
     */
    private String id;
    /**
     * 组的名称
     */
    private String groupName;
    /****************************扩展字段****************************/
    /**
     * 组的标识
     */
    private String groupSn;

}
