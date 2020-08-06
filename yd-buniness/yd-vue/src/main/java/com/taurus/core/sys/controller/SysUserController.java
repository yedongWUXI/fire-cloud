/**
 *
 *
 *
 *
 *
 */

package com.taurus.core.sys.controller;

import com.purgeteam.dispose.starter.Result;
import com.purgeteam.dispose.starter.exception.error.details.ResponseCode;
import com.taurus.common.annotation.SysLog;
import com.taurus.common.utils.Constant;
import com.taurus.common.utils.PageUtils;
import com.taurus.common.validator.Assert;
import com.taurus.common.validator.ValidatorUtils;
import com.taurus.common.validator.group.AddGroup;
import com.taurus.common.validator.group.UpdateGroup;
import com.taurus.core.sys.entity.SysUserEntity;
import com.taurus.core.sys.form.PasswordForm;
import com.taurus.core.sys.service.SysUserRoleService;
import com.taurus.core.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
@Api(tags = "系统用户")
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;


    /**
     * 所有用户列表
     */
    @ApiOperation(value = "所有用户列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }
        PageUtils page = sysUserService.queryPage(params);

        return page;
    }

    /**
     * 获取登录的用户信息
     */
    @ApiOperation(value = "获取登录的用户信息")
    @GetMapping("/info")
    public SysUserEntity info() {
        return getUser();
    }

    /**
     * 修改登录用户密码
     */
    @ApiOperation(value = "修改登录用户密码")
    @SysLog("修改密码")
    @PostMapping("/password")
    public Result password(@RequestBody PasswordForm form) {
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");

        //sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return Result.e("500", "原密码不正确");
        }

        return Result.e(ResponseCode.OK);
    }

    /**
     * 用户信息
     */
    @ApiOperation(value = "用户信息")
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public SysUserEntity info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.getById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return user;
    }

    /**
     * 保存用户
     */
    @ApiOperation(value = "保存用户")
    @SysLog("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public void save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.saveUser(user);

    }

    /**
     * 修改用户
     */
    @ApiOperation(value = "修改用户")
    @SysLog("修改用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public Result update(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.update(user);

        return Result.e(ResponseCode.OK);
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户")
    @SysLog("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return Result.e("500", "系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return Result.e("500", "当前用户不能删除");
        }

        sysUserService.deleteBatch(userIds);

        return Result.e(ResponseCode.OK);
    }
}
