package com.taurus.flow.dao.flowable;

import com.taurus.flow.vo.flowable.TaskQueryVo;
import com.taurus.flow.vo.flowable.ret.TaskVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @title: : IFlowableTaskDao
 * @projectName : flowable
 * @description: flowabletask的查询
 */
@Mapper
public interface IFlowableTaskDao {
    /**
     * 查询待办任务
     *
     * @param params 参数
     * @return
     */
    Page<TaskVo> getApplyingTasks(TaskQueryVo params);

    /**
     * 查询已办任务列表
     *
     * @param params 参数
     * @return
     */
    Page<TaskVo> getApplyedTasks(TaskQueryVo params);

}
