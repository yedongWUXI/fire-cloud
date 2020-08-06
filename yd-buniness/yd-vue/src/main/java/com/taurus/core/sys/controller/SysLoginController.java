/**
 */

package com.taurus.core.sys.controller;

import com.purgeteam.dispose.starter.Result;
import com.purgeteam.dispose.starter.exception.error.details.ResponseCode;
import com.taurus.core.sys.entity.SysUserEntity;
import com.taurus.core.sys.form.SysLoginForm;
import com.taurus.core.sys.service.SysCaptchaService;
import com.taurus.core.sys.service.SysUserService;
import com.taurus.core.sys.service.SysUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录相关
 */
@Api(tags = "登录相关")
@RestController
public class SysLoginController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SysCaptchaService sysCaptchaService;

    /**
     * 验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @ApiOperation(value = "登录")
    @PostMapping("/sys/login")
    public Result login(@RequestBody SysLoginForm form) throws IOException {
//		boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
//		if(!captcha){
//			return R.error("验证码不正确");
//		}

        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return Result.e(ResponseCode.SIGN_IN_INPUT_FAIL);
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return Result.e(ResponseCode.ACCOUNT_LOCKED);
        }

        //生成token，并保存到数据库
        return Result.e(ResponseCode.OK, sysUserTokenService.createToken(user.getUserId()));
    }


    /**
     * 退出
     */
    @ApiOperation(value = "退出")
    @PostMapping("/sys/logout")
    public void logout() {
        sysUserTokenService.logout(getUserId());
    }

//	/**
//	 * 获取token
//	 */
//	@ApiOperation(value = "获取token")
//	@PostMapping("/sys/getToken")
//	public R getToken() {
//		SysUserTokenEntity byId = sysUserTokenService.getById(getUserId());
//		return R.ok().put("total",byId);
//	}
//
}
