package com.taurus.flow.controller;

import com.taurus.flow.service.flowable.IFlowableProcessInstanceService;
import com.taurus.flow.vo.flowable.EndProcessVo;
import com.taurus.flow.vo.flowable.ProcessInstanceQueryVo;
import com.taurus.flow.vo.flowable.ret.ProcessInstanceVo;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @title: : ApiTask
 * @projectName : flowable
 * @description: 流程实例API
 */
@Api(tags = "流程实例管理")
@RestController
@RequestMapping("/rest/processInstance")
public class ApiFlowableProcessInstanceResource extends BaseResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiFlowableProcessInstanceResource.class);
    @Autowired
    private IFlowableProcessInstanceService flowableProcessInstanceService;

    /**
     * 分页查询流程定义列表
     *
     * @param params 参数
     * @param query  分页
     * @return
     */
    @ApiOperation(value = "查询")
    @PostMapping(value = "/page-model")
    public PagerModel<ProcessInstanceVo> pageModel(ProcessInstanceQueryVo params, Query query) {
        PagerModel<ProcessInstanceVo> pm = flowableProcessInstanceService.getPagerModel(params, query);
        return pm;
    }

    /**
     * 删除流程实例haha
     *
     * @param processInstanceId 参数
     * @return
     */
    @ApiOperation(value = "删除")
    @GetMapping(value = "/deleteProcessInstanceById/{processInstanceId}")
    public ReturnVo<String> deleteProcessInstanceById(@PathVariable String processInstanceId) {
        ReturnVo<String> data = flowableProcessInstanceService.deleteProcessInstanceById(processInstanceId);
        return data;
    }

    /**
     * 激活或者挂起流程定义
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "激活或挂起")
    @PostMapping(value = "/saProcessInstanceById")
    public ReturnVo<String> saProcessInstanceById(String id, int suspensionState) {
        ReturnVo<String> returnVo = flowableProcessInstanceService.suspendOrActivateProcessInstanceById(id, suspensionState);
        return returnVo;
    }

    /**
     * 终止
     *
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "终止")
    @PostMapping(value = "/stopProcess")
    public ReturnVo<String> stopProcess(EndProcessVo params) {
        boolean flag = this.isSuspended(params.getProcessInstanceId());
        ReturnVo<String> returnVo = null;
        if (flag) {
            params.setMessage("后台执行终止");
            params.setUserCode(this.getLoginUser().getId());
            returnVo = flowableProcessInstanceService.stopProcessInstanceById(params);
        } else {
            returnVo = new ReturnVo<>(ReturnCode.FAIL, "流程已挂起，请联系管理员激活!");
        }
        return returnVo;
    }

}
