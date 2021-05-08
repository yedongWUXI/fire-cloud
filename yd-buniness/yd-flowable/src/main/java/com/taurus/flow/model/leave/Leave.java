package com.taurus.flow.model.leave;

import com.dragon.tools.common.BaseModel;
import lombok.Data;

import java.util.Date;


/**
 * 请假单类
 *
 */
@Data
public class Leave extends BaseModel {
    /**
     *
     */
    private String id;
    /**
     * 流程实例id
     */
    private String processInstanceId;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private Integer days;
    /**
     *
     */
    private Date startTime;
    /**
     *
     */
    private Date endTime;

    // 临时变量 用于查询
    /**
     * 查询条件
     */
    private String keyWord;

}
