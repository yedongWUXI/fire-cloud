/**
 *
 *
 *
 *
 *
 */

package com.taurus.core.sys.controller;

import com.taurus.common.annotation.SysLog;
import com.taurus.common.utils.Constant;
import com.taurus.common.utils.PageUtils;
import com.taurus.common.validator.ValidatorUtils;
import com.taurus.core.sys.entity.SysRoleEntity;
import com.taurus.core.sys.service.SysRoleMenuService;
import com.taurus.core.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 */

@Api(tags = "角色管理")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 角色列表
     */
    @ApiOperation(value = "角色列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }

        return sysRoleService.queryPage(params);

    }

    /**
     * 角色列表
     */
    @ApiOperation(value = "角色列表")
    @GetMapping("/select")
    @RequiresPermissions("sys:role:select")
    public List<SysRoleEntity> select() {
        Map<String, Object> map = new HashMap<>();

        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            map.put("create_user_id", getUserId());
        }
        return sysRoleService.listByMap(map);

    }

    /**
     * 角色信息
     */
    @ApiOperation(value = "角色信息")
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public SysRoleEntity info(@PathVariable("roleId") Long roleId) {
        SysRoleEntity role = sysRoleService.getById(roleId);

        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        return role;
    }

    /**
     * 保存角色
     */
    @ApiOperation(value = "保存角色")
    @SysLog("保存角色")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public void save(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
        sysRoleService.saveRole(role);

    }

    /**
     * 修改角色
     */
    @ApiOperation(value = "修改角色")
    @SysLog("修改角色")
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public void update(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
        sysRoleService.update(role);

    }

    /**
     * 删除角色
     */
    @ApiOperation(value = "删除角色")
    @SysLog("删除角色")
    @PostMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public void delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);

    }
}
