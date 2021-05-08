package com.taurus.flow.vo.flowable.ret;

import java.io.Serializable;

/**
 * @title: : FormInfoVo
 * @projectName : flowable
 * @description: 表单的信息
 */
public class FormInfoVo<T> implements Serializable {
    /**
     * 表单信息
     */
    private T formInfo;
    /**
     * 任务id
     */
    private String taskId;
    /**
     * 流程实例id
     */
    private String processInstanceId;

    public FormInfoVo() {
    }

    public FormInfoVo(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public FormInfoVo(String taskId, String processInstanceId) {
        this.taskId = taskId;
        this.processInstanceId = processInstanceId;
    }

    public T getFormInfo() {
        return formInfo;
    }

    public void setFormInfo(T formInfo) {
        this.formInfo = formInfo;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
