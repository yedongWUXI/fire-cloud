package com.taurus.flow.model.leave;

import com.dragon.tools.common.BaseModel;
import lombok.Data;

import java.util.Date;


/**
 * 采购类
 *
 */
@Data
public class Purchase extends BaseModel {
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
    private String title;
    /**
     *
     */
    private String content;
    /**
     *
     */
    private Double money;
    /**
     *
     */
    private Date applyTime;

    // 临时变量 用于查询
    /**
     * 查询条件
     */
    private String keyWord;

}
