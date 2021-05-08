package com.taurus.flow.dao.flowable;

import com.taurus.flow.vo.flowable.ProcessDefinitionQueryVo;
import com.taurus.flow.vo.flowable.ret.ProcessDefinitionVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @title: : IFlowableProcessInstentDao
 * @projectName : flowable
 * @description: 流程定义Dao
 */
@Mapper
public interface IFlowableProcessDefinitionDao {

    /**
     * 通过条件查询流程定义列表
     *
     * @param params 参数
     * @return
     */
    Page<ProcessDefinitionVo> getPagerModel(ProcessDefinitionQueryVo params);

    /**
     * 通过流程定义id获取流程定义的信息
     *
     * @param processDefinitionId 流程定义id
     * @return
     */
    ProcessDefinitionVo getById(String processDefinitionId);
}
