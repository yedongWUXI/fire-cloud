package com.taurus.flow.service.flowable.impl;

import com.taurus.flow.cmd.AddHisCommentCmd;
import com.taurus.flow.dao.flowable.IFlowableCommentDao;
import com.taurus.flow.enu.CommentTypeEnum;
import com.taurus.flow.service.flowable.IFlowableCommentService;
import com.taurus.flow.vo.flowable.ret.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @title: : FlowCommentServiceImpl
 * @projectName : flowable
 * @description: 流程备注service
 */
@Service
public class FlowableCommentServiceImpl extends BaseProcessService implements IFlowableCommentService {

    @Autowired
    private IFlowableCommentDao flowableCommentDao;

    @Override
    public void addComment(CommentVo comment) {
        managementService.executeCommand(new AddHisCommentCmd(comment.getTaskId(), comment.getUserId(), comment.getProcessInstanceId(),
                comment.getType(), comment.getMessage()));
    }

    @Override
    public List<CommentVo> getFlowCommentVosByProcessInstanceId(String processInstanceId) {
        List<CommentVo> datas = flowableCommentDao.getFlowCommentVosByProcessInstanceId(processInstanceId);
        datas.forEach(commentVo -> {
            commentVo.setTypeName(CommentTypeEnum.getEnumMsgByType(commentVo.getType()));
        });
        return datas;
    }
}
