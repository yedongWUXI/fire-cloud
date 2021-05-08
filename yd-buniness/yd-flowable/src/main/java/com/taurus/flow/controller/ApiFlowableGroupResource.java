package com.taurus.flow.controller;

import com.taurus.flow.service.flowable.IFlowableIdentityService;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.flowable.idm.api.*;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title: : ApiFlowableIdentityResource
 * @projectName : flowable
 * @description: 用户组
 */
@Api(tags = "用户组")
@RestController
@RequestMapping("/rest/group")
public class ApiFlowableGroupResource extends BaseResource {

    @Autowired
    private IdmIdentityService idmIdentityService;
    @Autowired
    private IFlowableIdentityService flowableIdentityService;

    /**
     * 查询用户组列表
     *
     * @param name 姓名
     * @return
     */
    @ApiOperation(value = "查询用户组列表")
    @GetMapping("/getPagerModel")
    public PagerModel<Group> getPagerModel(String name, Query query) {
        GroupQuery groupQuery = idmIdentityService.createGroupQuery().groupNameLike(name);
        long count = groupQuery.count();
        int firstResult = (query.getPageNum() - 1) * query.getPageSize();
        List<Group> datas = groupQuery.orderByGroupName().listPage(firstResult, query.getPageSize());
        return new PagerModel<>(count, datas);
    }

    /**
     * 添加修改组
     *
     * @param group 组
     * @return
     */
    @ApiOperation(value = "添加修改组")
    @PostMapping("/save")
    public ReturnVo<String> save(GroupEntityImpl group) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.SUCCESS, "添加成功");
        flowableIdentityService.saveGroup(group);
        return returnVo;
    }

    /**
     * 删除组
     *
     * @param groupId
     * @return
     */
    @ApiOperation(value = "删除组")
    @PostMapping("/delete")
    public ReturnVo<String> delete(String groupId) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.SUCCESS, "删除成功");
        idmIdentityService.deleteGroup(groupId);
        return returnVo;
    }

    /**
     * 添加组
     *
     * @param groupId 组的id
     * @param userIds 用户的ids
     * @return
     */
    @ApiOperation(value = "添加组")
    @PostMapping("/addGroupUser")
    public ReturnVo<String> addUserGroup(String groupId, List<String> userIds) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.SUCCESS, "删除成功");
        if (CollectionUtils.isNotEmpty(userIds)) {
            userIds.forEach(userId -> {
                idmIdentityService.createMembership(userId, groupId);
            });
        }
        return returnVo;
    }
}
