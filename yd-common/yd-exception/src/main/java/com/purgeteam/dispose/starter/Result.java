package com.purgeteam.dispose.starter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.purgeteam.dispose.starter.exception.error.CommonErrorCode;
import com.purgeteam.dispose.starter.exception.error.details.BusinessErrorCode;
import com.purgeteam.dispose.starter.exception.error.details.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回统一数据结构
 *
 * @author purgeyao
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result<T> implements Serializable {

    /**
     * 成功数据
     */
    private T data;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String msg;


    public static <T> Result<T> e(ResponseCode statusEnum) {
        return e(statusEnum, null);
    }

    public static <T> Result<T> e(ResponseCode statusEnum, T data) {
        Result<T> res = new Result<>();
        res.setCode(statusEnum.code);
        res.setMsg(statusEnum.msg);
        res.setData(data);
        return res;
    }


    public static <T> Result<T> e(CommonErrorCode statusEnum) {
        return e(statusEnum, null);
    }

    public static <T> Result<T> e(CommonErrorCode statusEnum, T data) {
        Result<T> res = new Result<>();
        res.setCode(statusEnum.code);
        res.setMsg(statusEnum.msg);
        res.setData(data);
        return res;
    }

    public static <T> Result<T> e(BusinessErrorCode statusEnum) {
        return e(statusEnum, null);
    }

    public static <T> Result<T> e(BusinessErrorCode statusEnum, T data) {
        Result<T> res = new Result<>();
        res.setCode(statusEnum.code);
        res.setMsg(statusEnum.msg);
        res.setData(data);
        return res;
    }


//    public static <T> Result<T> ofSuccess(ResponseCode responseCode) {
//        Result result = new Result();
//        return result;
//    }

//    public static <T> Result<T> ofSuccess(Object data) {
//        Result result = new Result();
//        result.setData(data);
//        return result;
//    }


    public static <T> Result<T> e(String code, String msg) {
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> e(String code, String msg, Object data) {
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        result.setData(data);
        return result;
    }
//
//    public static <T> Result<T> ofFail(CommonErrorCode resultEnum) {
//        Result result = new Result();
//        result.code = resultEnum.code;
//        result.msg = resultEnum.msg;
//        return result;
//    }

    /**
     * 获取 json
     */
    public String buildResultJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", this.code);
        jsonObject.put("msg", this.msg);
        jsonObject.put("data", this.data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public String toString() {
        return "Result{" +
                " data=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
