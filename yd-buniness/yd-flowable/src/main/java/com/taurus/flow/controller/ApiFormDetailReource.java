package com.taurus.flow.controller;

import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.vo.ReturnVo;
import com.taurus.flow.service.flowable.IFlowableCommentService;
import com.taurus.flow.service.flowable.IFlowableProcessInstanceService;
import com.taurus.flow.service.flowable.IFlowableTaskService;
import com.taurus.flow.vo.flowable.*;
import com.taurus.flow.vo.flowable.ret.CommentVo;
import com.taurus.flow.vo.flowable.ret.FlowNodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @title: : ApiFlowCommentReource
 * @projectName : flowable
 * @description: 备注
 */
@Api(tags = "表单审批")
@RestController
@RequestMapping("/rest/formdetail")
public class ApiFormDetailReource extends BaseResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiFormDetailReource.class);
    @Autowired
    private IFlowableCommentService flowableCommentService;
    @Autowired
    private IFlowableTaskService flowableTaskService;
    @Autowired
    private IFlowableProcessInstanceService flowableProcessInstanceService;

    /**
     * 通过流程实例id获取审批意见
     *
     * @param processInstanceId 流程实例id
     * @return
     */
    @ApiOperation(value = "通过流程实例id获取审批意见")
    @GetMapping("/commentsByProcessInstanceId")
    public List<CommentVo> commentsByProcessInstanceId(String processInstanceId) {
        List<CommentVo> datas = flowableCommentService.getFlowCommentVosByProcessInstanceId(processInstanceId);
        return datas;
    }

    /**
     * 审批任务
     *
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "审批任务")
    @PostMapping(value = "/complete")
    public ReturnVo<String> complete(CompleteTaskVo params) {
        boolean flag = this.isSuspended(params.getProcessInstanceId());
        ReturnVo<String> returnVo = null;
        if (flag) {
            params.setUserCode(this.getLoginUser().getId());
            returnVo = flowableTaskService.complete(params);
        } else {
            returnVo = new ReturnVo<>(ReturnCode.FAIL, "流程已挂起，请联系管理员激活!");
        }
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
            params.setUserCode(this.getLoginUser().getId());
            returnVo = flowableProcessInstanceService.stopProcessInstanceById(params);
        } else {
            returnVo = new ReturnVo<>(ReturnCode.FAIL, "流程已挂起，请联系管理员激活!");
        }
        return returnVo;
    }

    /**
     * 撤回
     *
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "撤回")
    @PostMapping(value = "/revokeProcess")
    public ReturnVo<String> revokeProcess(RevokeProcessVo params) {
        params.setUserCode(this.getLoginUser().getId());
        ReturnVo<String> returnVo = flowableProcessInstanceService.revokeProcess(params);
        return returnVo;
    }

    /**
     * 转办
     *
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "转办")
    @PostMapping(value = "/turnTask")
    public ReturnVo<String> turnTask(TurnTaskVo params, String[] userCodes) {
        ReturnVo<String> returnVo = null;
        if (userCodes != null && userCodes.length > 0) {
            params.setUserCode(this.getLoginUser().getId());
            params.setTurnToUserId(userCodes[0]);
            returnVo = flowableTaskService.turnTask(params);
        } else {
            returnVo = new ReturnVo<>(ReturnCode.FAIL, "请选择人员");
        }
        return returnVo;
    }

    /**
     * 委派
     *
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "委派")
    @PostMapping(value = "/delegateTask")
    public ReturnVo<String> delegateTask(DelegateTaskVo params, String[] userCodes) {
        ReturnVo<String> returnVo = null;
        if (userCodes != null && userCodes.length > 0) {
            params.setUserCode(this.getLoginUser().getId());
            params.setDelegateUserCode(userCodes[0]);
            returnVo = flowableTaskService.delegateTask(params);
        } else {
            returnVo = new ReturnVo<>(ReturnCode.FAIL, "请选择人员");
        }
        return returnVo;
    }

    /**
     * 签收
     *
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "签收")
    @PostMapping(value = "/claimTask")
    public ReturnVo<String> claimTask(ClaimTaskVo params) {
        params.setUserCode(this.getLoginUser().getId());
        ReturnVo<String> returnVo = flowableTaskService.claimTask(params);
        return returnVo;
    }

    /**
     * 反签收
     *
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "反签收")
    @PostMapping(value = "/unClaimTask")
    public ReturnVo<String> unClaimTask(ClaimTaskVo params) {
        params.setUserCode(this.getLoginUser().getId());
        ReturnVo<String> returnVo = flowableTaskService.unClaimTask(params);
        return returnVo;
    }

    /**
     * 向前加签
     *
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "向前加签")
    @PostMapping(value = "/beforeAddSignTask")
    public ReturnVo<String> beforeAddSignTask(AddSignTaskVo params, String[] userCodes) {
        ReturnVo<String> returnVo = null;
        if (userCodes != null && userCodes.length > 0) {
            params.setUserCode(this.getLoginUser().getId());
            params.setSignPersoneds(Arrays.asList(userCodes));
            returnVo = flowableTaskService.beforeAddSignTask(params);
        } else {
            returnVo = new ReturnVo<>(ReturnCode.FAIL, "请选择人员");
        }
        return returnVo;
    }

    /**
     * 向后加签
     *
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "向后加签")
    @PostMapping(value = "/afterAddSignTask")
    public ReturnVo<String> afterAddSignTask(AddSignTaskVo params, String[] userCodes) {
        ReturnVo<String> returnVo = null;
        if (userCodes != null && userCodes.length > 0) {
            params.setUserCode(this.getLoginUser().getId());
            params.setSignPersoneds(Arrays.asList(userCodes));
            returnVo = flowableTaskService.afterAddSignTask(params);
        } else {
            returnVo = new ReturnVo<>(ReturnCode.FAIL, "请选择人员");
        }
        return returnVo;
    }

    @ApiOperation(value = "跟踪当前任务节点")
    @GetMapping(value = "/image/{processInstanceId}")
    public void image(@PathVariable String processInstanceId, HttpServletResponse response) {
        try {
            byte[] b = flowableProcessInstanceService.createImage(processInstanceId);
            response.setHeader("Content-type", "text/xml;charset=UTF-8");
            response.getOutputStream().write(b);
        } catch (Exception e) {
            LOGGER.error("ApiFormDetailReource-image:" + e);
            e.printStackTrace();
        }
    }

    /**
     * 获取可驳回节点列表
     *
     * @param processInstanceId 流程实例id
     * @return
     */
    @ApiOperation(value = "获取可驳回节点列表")
    @GetMapping(value = "/getBackNodesByProcessInstanceId/{processInstanceId}/{taskId}")
    public ReturnVo<FlowNodeVo> getBackNodesByProcessInstanceId(@PathVariable String processInstanceId, @PathVariable String taskId) {
        List<FlowNodeVo> datas = flowableTaskService.getBackNodesByProcessInstanceId(processInstanceId, taskId);
        ReturnVo<FlowNodeVo> returnVo = new ReturnVo<>(ReturnCode.SUCCESS, "查询返回节点成功");
        returnVo.setDatas(datas);
        return returnVo;

    }

    /**
     * 驳回节点
     *
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "驳回节点")
    @PostMapping(value = "/doBackStep")
    public ReturnVo<String> doBackStep(BackTaskVo params) {
        params.setUserCode(this.getLoginUser().getId());
        ReturnVo<String> returnVo = flowableTaskService.backToStepTask(params);
        return returnVo;
    }
}
