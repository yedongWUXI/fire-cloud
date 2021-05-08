package com.taurus.flow.controller;

import com.taurus.flow.model.leave.Leave;
import com.taurus.flow.service.flowable.IFlowableProcessInstanceService;
import com.taurus.flow.service.leave.ILeaveService;
import com.taurus.flow.vo.flowable.StartProcessInstanceVo;
import com.dragon.tools.common.UUIDGenerator;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import com.dragon.tools.common.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.User;
import org.flowable.ui.common.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * description : 请假单Controller
 */
@Api(tags = "请假")
@RestController
@RequestMapping("/rest/leave")
public class LeaveResource extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(LeaveResource.class);

    private final String nameSpace = "leave";

    @Autowired
    private ILeaveService LeaveService;
    @Autowired
    private IFlowableProcessInstanceService flowableProcessInstanceService;

    @ApiOperation(value = "查询")
    @GetMapping("/list")
    public PagerModel<Leave> list(Leave Leave, Query query, String sessionId) {
        PagerModel<Leave> pm = null;
        try {
            pm = this.LeaveService.getPagerModelByQuery(Leave, query);
        } catch (Exception e) {
            logger.error("LeaveController-ajaxList:", e);
            e.printStackTrace();
        }
        return pm;
    }

    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public ReturnVo add(Leave leave, String sessionId) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.FAIL, "添加失败");
        try {
            String leaveId = UUIDGenerator.generate();
            leave.setId(leaveId);
            StartProcessInstanceVo startProcessInstanceVo = new StartProcessInstanceVo();
            startProcessInstanceVo.setBusinessKey(leaveId);
            User user = SecurityUtils.getCurrentUserObject();
            startProcessInstanceVo.setCreator(user.getId());
            startProcessInstanceVo.setCurrentUserCode(user.getId());
            startProcessInstanceVo.setFormName("请假流程");
            startProcessInstanceVo.setSystemSn("flow");
//            startProcessInstanceVo.setProcessDefinitionKey("leave");
            startProcessInstanceVo.setProcessDefinitionKey("leave-ExclusiveGateWay");
            Map<String, Object> variables = new HashMap<>();
            //这里的变量可以被flowable接收到 作为动态变量做出判断
            variables.put("days", leave.getDays());
            startProcessInstanceVo.setVariables(variables);
            //设置三个人作为多实例的人员
            List<String> userList = new ArrayList<>();
            userList.add("00000005");
            userList.add("00000006");
            variables.put("userList", userList);

            //启动流程
            ReturnVo<ProcessInstance> returnStart = flowableProcessInstanceService.startProcessInstanceByKey(startProcessInstanceVo);
            if (returnStart.getCode().equals(ReturnCode.SUCCESS)) {
                String processInstanceId = returnStart.getData().getProcessInstanceId();
                leave.setProcessInstanceId(processInstanceId);
                //插入自定义业务表
                this.LeaveService.insertLeave(leave);
                returnVo = new ReturnVo(ReturnCode.SUCCESS, "添加成功");
            } else {
                returnVo = new ReturnVo(returnStart.getCode(), returnStart.getMsg());
            }
        } catch (Exception e) {
            logger.error("LeaveController-add:", e);
            e.printStackTrace();
        }
        return returnVo;
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public ReturnVo update(Leave Leave, String sessionId) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.FAIL, "修改失败");
        try {
            this.LeaveService.updateLeave(Leave);
            returnVo = new ReturnVo(ReturnCode.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error("LeaveController-update:", e);
            e.printStackTrace();
        }
        return returnVo;
    }


}
