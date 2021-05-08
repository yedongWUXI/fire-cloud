package com.taurus.flow.service.flowable;

import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;

/**
 * @projectName : flowable
 * @description: 用户组
 */
public interface IFlowableIdentityService {
    /**
     * 添加用户
     *
     * @param user
     */
    public void saveUser(User user);

    /**
     * 添加组
     *
     * @param group
     */
    public void saveGroup(Group group);


}
