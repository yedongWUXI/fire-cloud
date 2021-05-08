package com.taurus.flow.controller;

import com.dragon.tools.common.ReturnCode;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.vo.ReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.Privilege;
import org.flowable.idm.api.PrivilegeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title: : ApiFlowableIdentityResource
 * @projectName : flowable
 * @description: 权限
 */
@Api(tags = "权限相关")
@RestController
@RequestMapping("/rest/privilege")
public class ApiFlowablePrivilegeResource extends BaseResource {

    @Autowired
    private IdmIdentityService idmIdentityService;

    /**
     * 查询权限列表
     *
     * @param name 姓名
     * @return
     */
    @ApiOperation(value = "查询权限列表")
    @GetMapping("/getPagerModel")
    public PagerModel<Privilege> getPagerModel(String name, Query query) {
        PrivilegeQuery privilegeQuery = idmIdentityService.createPrivilegeQuery().privilegeName(name);
        long count = privilegeQuery.count();
        int firstResult = (query.getPageNum() - 1) * query.getPageSize();
        List<Privilege> datas = privilegeQuery.listPage(firstResult, query.getPageSize());
        return new PagerModel<>(count, datas);
    }

    /**
     * 添加修改权限
     *
     * @param privilegeName 名称
     * @return
     */
    @ApiOperation(value = "添加修改权限")
    @PostMapping("/save")
    public ReturnVo<String> save(String privilegeName) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.SUCCESS, "添加成功");
        idmIdentityService.createPrivilege(privilegeName);
        return returnVo;
    }

    /**
     * 删除权限
     *
     * @param privilegeId 权限id
     * @return
     */
    @ApiOperation(value = "删除权限")
    @PostMapping("/delete")
    public ReturnVo<String> delete(String privilegeId) {
        ReturnVo returnVo = new ReturnVo(ReturnCode.SUCCESS, "删除成功");
        idmIdentityService.deletePrivilege(privilegeId);
        return returnVo;
    }
}
