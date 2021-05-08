package com.taurus.flow.dao.flowable;

import com.taurus.flow.vo.flowable.ProcessInstanceQueryVo;
import com.taurus.flow.vo.flowable.ret.ProcessInstanceVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @title: : IFlowableProcessInstentDao
 * @projectName : flowable
 */
@Mapper
public interface IFlowableProcessInstanceDao {

    /**
     * 通过条件查询流程实例VO对象列表
     *
     * @param params 参数
     * @return
     */
    Page<ProcessInstanceVo> getPagerModel(ProcessInstanceQueryVo params);
}
