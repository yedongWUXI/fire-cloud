package com.taurus.flow.service.flowable.impl;

import com.taurus.flow.dao.flowable.IFlowableProcessDefinitionDao;
import com.taurus.flow.service.flowable.IFlowableProcessDefinitionService;
import com.taurus.flow.vo.flowable.ProcessDefinitionQueryVo;
import com.taurus.flow.vo.flowable.ret.ProcessDefinitionVo;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @title: : FlowableProcessDefinitionServiceImpl
 * @projectName : flowable
 * @description: 流程定义service
 */
@Service
public class FlowableProcessDefinitionServiceImpl extends BaseProcessService implements IFlowableProcessDefinitionService {

    @Autowired
    private IFlowableProcessDefinitionDao flowableProcessDefinitionDao;

    @Override
    public PagerModel<ProcessDefinitionVo> getPagerModel(ProcessDefinitionQueryVo params, Query query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        Page<ProcessDefinitionVo> page = flowableProcessDefinitionDao.getPagerModel(params);
        return new PagerModel<>(page);
    }

    @Override
    public ProcessDefinitionVo getById(String processDefinitionId) {
        return flowableProcessDefinitionDao.getById(processDefinitionId);
    }

    @Override
    public ReturnVo suspendOrActivateProcessDefinitionById(String processDefinitionId, int suspensionState) {
        ReturnVo returnVo = null;
        if (suspensionState == 1) {
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
            returnVo = new ReturnVo(ReturnCode.SUCCESS, "挂起成功");
        } else {
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
            returnVo = new ReturnVo(ReturnCode.SUCCESS, "激活成功");
        }
        return returnVo;
    }

    public void getLine() {

    }

}
