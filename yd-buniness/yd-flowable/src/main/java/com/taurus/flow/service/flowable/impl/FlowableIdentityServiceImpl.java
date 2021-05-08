package com.taurus.flow.service.flowable.impl;

import com.taurus.flow.service.flowable.IFlowableIdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.stereotype.Service;

/**
 * @title: : FlowableIdentityServiceImpl
 * @projectName : flowable
 * @description: 用户组service
 */
@Service
public class FlowableIdentityServiceImpl extends BaseProcessService implements IFlowableIdentityService {

    @Override
    public void saveUser(User user) {
        identityService.saveUser(user);
    }

    @Override
    public void saveGroup(Group group) {
        identityService.saveGroup(group);
    }

}
