package com.taurus.flow.controller;

import com.taurus.flow.service.flowable.IFlowableIdentityService;
import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.flowable.idm.api.*;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @title: : ApiFlowableIdentityResource
 * @projectName : flowable
 * @description: 用户管理
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/rest/user")
public class ApiFlowableUserResource extends BaseResource {

    @Autowired
    private IdmIdentityService idmIdentityService;
    @Autowired
    private IFlowableIdentityService flowableIdentityService;

    /**
     * 查询用户列表
     *
     * @param name 姓名
     * @return
     */
    @ApiOperation(value = "查询用户列表")
    @GetMapping("/getPagerModel")
    public PagerModel<UserEntityImpl> getPagerModel(String name, Query query) {
        UserQuery userQuery = idmIdentityService.createUserQuery();
        if (StringUtils.isNotBlank(name)) {
            userQuery.userFirstNameLike(name);
        }
        long count = userQuery.count();

        int firstResult = (query.getPageNum() - 1) * query.getPageSize();
        List<User> datas = userQuery.orderByUserFirstName().desc().listPage(firstResult, query.getPageSize());
        List<UserEntityImpl> list = new ArrayList<>();
        datas.forEach(v -> {
            UserEntityImpl userEntity = new UserEntityImpl();
            userEntity.setId(v.getId());
            userEntity.setDisplayName(v.getDisplayName());
            userEntity.setFirstName(v.getFirstName());
            userEntity.setLastName(v.getLastName());
            userEntity.setEmail(v.getEmail());
            list.add(userEntity);

        });
        return new PagerModel<>(count, list);
    }

    /**
     * 添加修改用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "添加或修改")
    @PostMapping("/save")
    public ReturnVo<String> save(@RequestBody UserEntityImpl user) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.SUCCESS, "添加成功");
        long count = idmIdentityService.createUserQuery().userId(user.getId()).count();
        flowableIdentityService.saveUser(user);
        if (count == 0) {
            Privilege privilege = idmIdentityService.createPrivilegeQuery().privilegeName("access-idm").singleResult();
            idmIdentityService.addUserPrivilegeMapping(privilege.getId(), user.getId());
        }
        return returnVo;
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ReturnVo<String> delete(String userId) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.SUCCESS, "删除成功");
        idmIdentityService.deleteUser(userId);
        return returnVo;
    }

    /**
     * 添加用户
     *
     * @param userId   用户id
     * @param groupIds 组ids
     * @return
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("/addUserGroup")
    public ReturnVo<String> addUserGroup(String userId, List<String> groupIds) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.SUCCESS, "删除成功");
        if (CollectionUtils.isNotEmpty(groupIds)) {
            groupIds.forEach(groupId -> idmIdentityService.createMembership(userId, groupId));
        }
        return returnVo;
    }
}
