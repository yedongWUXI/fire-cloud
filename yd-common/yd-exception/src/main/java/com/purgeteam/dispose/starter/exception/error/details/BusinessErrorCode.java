package com.purgeteam.dispose.starter.exception.error.details;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 业务通用异常枚举
 *
 * @author purgeyao
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public enum BusinessErrorCode {

    /**
     * 通用业务异常
     */
    BUSINESS_ERROR("CLOUD800", "业务异常"),
    ;

    public String code;

    public String msg;



}
