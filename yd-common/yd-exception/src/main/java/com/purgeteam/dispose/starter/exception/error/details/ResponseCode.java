package com.purgeteam.dispose.starter.exception.error.details;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseCode {

    OK("0", "操作成功"),
    SIGN_IN_OK("2", "登录成功"),
    LOGOUT_OK("3", "注销登录成功"),
    ACCOUNT_LOCKED("-5", "账号已被锁定,请联系管理员"),
    SIGN_IN_INPUT_FAIL("-4", "账号或密码错误"),
    SIGN_IN_FAIL("-3", "登录失败"),
    FAIL("-1", "操作失败"),
    LOGOUT_FAIL("-2", "注销登录失败"),
    UNAUTHORIZEDEXCEPTION("-6", "没有权限"),
    SING_IN_INPUT_EMPTY("-5", "账户和密码均不能为空"),
    NOT_SING_IN("-6", "用户未登录或身份异常");

    public String code;

    public String msg;

}
