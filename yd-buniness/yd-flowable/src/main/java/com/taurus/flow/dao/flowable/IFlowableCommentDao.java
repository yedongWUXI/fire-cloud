package com.taurus.flow.dao.flowable;

import com.taurus.flow.vo.flowable.ret.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName : flowable
 * @description: 流程备注Dao
 */
@Mapper
public interface IFlowableCommentDao {

    /**
     * 通过流程实例id获取审批意见列表
     *
     * @param ProcessInstanceId 流程实例id
     * @return
     */
    List<CommentVo> getFlowCommentVosByProcessInstanceId(String ProcessInstanceId);

}
