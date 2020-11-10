package com.taurus.core.dictionary.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 *
 * @author yeDong
 * @email yeDong@gmail.com
 * @date 2020-09-08 18:19:10
 */
@Data
@TableName("dictionary")
public class DictionaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * id
     */
        @TableId
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;
    /**
     * 具体值
     */
    @ApiModelProperty(value = "具体值")
    private String value;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createtime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatetime;
    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private String createid;
    /**
     * 更新人id
     */
    @ApiModelProperty(value = "更新人id")
    private String updateid;


    public static final String COL_ID = "id" ;

    public static final String COL_TYPE = "type" ;

    public static final String COL_VALUE = "value" ;

    public static final String COL_NAME = "name" ;

    public static final String COL_CREATETIME = "createtime" ;

    public static final String COL_UPDATETIME = "updatetime" ;

    public static final String COL_CREATEID = "createid" ;

    public static final String COL_UPDATEID = "updateid" ;

}
