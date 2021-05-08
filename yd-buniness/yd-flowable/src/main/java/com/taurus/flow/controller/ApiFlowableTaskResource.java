package com.taurus.flow.controller;

import com.taurus.flow.model.leave.Leave;
import com.taurus.flow.service.flowable.IFlowableProcessInstanceService;
import com.taurus.flow.service.flowable.IFlowableTaskService;
import com.taurus.flow.service.leave.ILeaveService;
import com.taurus.flow.vo.flowable.TaskQueryVo;
import com.taurus.flow.vo.flowable.ret.FormInfoVo;
import com.taurus.flow.vo.flowable.ret.ProcessInstanceVo;
import com.taurus.flow.vo.flowable.ret.TaskVo;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import com.taurus.flow.vo.flowable.FormInfoQueryVo;
import com.taurus.flow.vo.flowable.ProcessInstanceQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: : ApiFlowableTaskResource
 * @projectName : flowable
 * @description: 任务列表
 */
@Api(tags = "任务列表")
@RestController
@RequestMapping("/rest/task")
public class ApiFlowableTaskResource extends BaseResource {

    @Autowired
    private IFlowableTaskService flowableTaskService;
    @Autowired
    private IFlowableProcessInstanceService flowableProcessInstanceService;
    @Autowired
    private ILeaveService leaveService;
    @Autowired
    private HistoryService historyService;

    /**
     * 获取待办任务列表
     *
     * @param params 参数
     * @param query  查询条件
     * @return
     */
    @ApiOperation(value = "获取待办任务列表")
    @GetMapping(value = "/get-applying-tasks")
    public PagerModel<TaskVo> getApplyingTasks(TaskQueryVo params, Query query) {
        params.setUserCode(this.getLoginUser().getId());
        PagerModel<TaskVo> pm = flowableTaskService.getApplyingTasks(params, query);
        return pm;
    }

    /**
     * 获取已办任务列表
     *
     * @param params 参数
     * @param query  查询条件
     * @return
     */
    @ApiOperation(value = "获取已办任务列表")
    @GetMapping(value = "/get-applyed-tasks")
    public PagerModel<TaskVo> getApplyedTasks(TaskQueryVo params, Query query) {
        params.setUserCode(this.getLoginUser().getId());
        PagerModel<TaskVo> pm = flowableTaskService.getApplyedTasks(params, query);
        return pm;
    }

    /**
     * 我发起的流程
     *
     * @param params 参数
     * @param query  查询条件
     * @return
     */
    @ApiOperation(value = "我发起的流程")
    @GetMapping(value = "/my-processInstances")
    public PagerModel<ProcessInstanceVo> myProcessInstances(ProcessInstanceQueryVo params, Query query) {
        params.setUserCode(this.getLoginUser().getId());
        PagerModel<ProcessInstanceVo> pm = flowableProcessInstanceService.getMyProcessInstances(params, query);
        return pm;
    }


    /**
     * 查询表单详情
     *
     * @param params 参数
     * @return
     */

    @ApiOperation(value = "办理")
    @PostMapping(value = "/find-formInfo")
    public ReturnVo<FormInfoVo> findFormInfoByFormInfoQueryVo(FormInfoQueryVo params) throws Exception {
        ReturnVo<FormInfoVo> returnVo = new ReturnVo<>(ReturnCode.SUCCESS, "OK");
        FormInfoVo<Leave> formInfoVo = new FormInfoVo(params.getTaskId(), params.getProcessInstanceId());
        String processInstanceId = params.getProcessInstanceId();
        String businessKey = null;
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            businessKey = historicProcessInstance.getBusinessKey();
        } else {
            businessKey = processInstance.getBusinessKey();
        }
        Leave leave = leaveService.getLeaveById(businessKey);
        formInfoVo.setFormInfo(leave);
        returnVo.setData(formInfoVo);
        return returnVo;
    }

}
